package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.model.CartItem
import com.example.model.OrderConfirmation
import com.example.model.PaymentType
import com.example.model.ShippingMethod
import com.example.ui.theme.TechAccent
import com.example.ui.theme.TechPrimary
import com.example.ui.theme.TechSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.viewmodel.StoreViewModel
import java.util.Locale

@Composable
fun CartAndCheckoutModal(
    viewModel: StoreViewModel,
    onDismiss: () -> Unit
) {
    val isCartOpen by viewModel.isCartOpen.collectAsState()
    val isCheckoutOpen by viewModel.isCheckoutOpen.collectAsState()
    val completedOrder by viewModel.completedOrder.collectAsState()

    if (!isCartOpen && !isCheckoutOpen && completedOrder == null) {
        return
    }

    Dialog(
        onDismissRequest = {
            if (completedOrder != null) {
                viewModel.resetCompletedOrder()
            }
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxSize(0.92f)
                .testTag("cart_checkout_modal_container"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            when {
                completedOrder != null -> {
                    OrderConfirmationView(
                        order = completedOrder!!,
                        viewModel = viewModel,
                        onClose = {
                            viewModel.resetCompletedOrder()
                            onDismiss()
                        }
                    )
                }
                isCheckoutOpen -> {
                    CheckoutPaymentGatewayView(
                        viewModel = viewModel,
                        onBackToCart = {
                            viewModel.closeCheckout()
                            viewModel.openCart()
                        },
                        onClose = onDismiss
                    )
                }
                else -> {
                    CartView(
                        viewModel = viewModel,
                        onProceedToCheckout = {
                            viewModel.openCheckout()
                        },
                        onClose = onDismiss
                    )
                }
            }
        }
    }
}

@Composable
fun CartView(
    viewModel: StoreViewModel,
    onProceedToCheckout: () -> Unit,
    onClose: () -> Unit
) {
    val items by viewModel.cartItems.collectAsState()
    val checkoutData by viewModel.checkoutData.collectAsState()
    val appliedCoupon by viewModel.appliedCoupon.collectAsState()
    val couponDiscountPercent by viewModel.couponDiscountPercent.collectAsState()

    var couponInput by remember { mutableStateOf("") }
    var couponError by remember { mutableStateOf(false) }

    val subtotal = items.sumOf { it.totalPrice }
    val discount = subtotal * (couponDiscountPercent / 100.0)
    val shipping = if (items.isNotEmpty()) checkoutData.shippingMethod.costSoles else 0.0
    val total = (subtotal - discount + shipping).coerceAtLeast(0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        // Cart Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = null,
                    tint = TechPrimary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Carrito de Compras",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    shape = CircleShape,
                    color = TechPrimary.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "${items.sumOf { it.quantity }}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = TechPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Cerrar")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (items.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Tu carrito está vacío",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Explora monitores, procesadores, teclados y periféricos para agregar.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            // Cart Items List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items, key = { it.product.id }) { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.updateCartQuantity(item.product.id, 1) },
                        onDecrease = { viewModel.updateCartQuantity(item.product.id, -1) },
                        onRemove = { viewModel.removeFromCart(item.product.id) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Shipping Options Selector
                    Text(
                        text = "Tipo de Envío:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    ShippingMethod.values().forEach { method ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    viewModel.updateCheckoutData { it.copy(shippingMethod = method) }
                                },
                            shape = RoundedCornerShape(10.dp),
                            color = if (checkoutData.shippingMethod == method)
                                TechPrimary.copy(alpha = 0.12f)
                            else MaterialTheme.colorScheme.surfaceVariant,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (checkoutData.shippingMethod == method) TechPrimary else Color.Transparent
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = checkoutData.shippingMethod == method,
                                    onClick = {
                                        viewModel.updateCheckoutData { it.copy(shippingMethod = method) }
                                    },
                                    colors = RadioButtonDefaults.colors(selectedColor = TechPrimary)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = method.displayName,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Tiempo: ${method.estimatedDelivery}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Text(
                                    text = if (method.costSoles > 0) "S/. ${String.format(Locale.US, "%.2f", method.costSoles)}" else "GRATIS",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (method.costSoles > 0) TechPrimary else Color(0xFF10B981)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Coupon Input
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = couponInput,
                            onValueChange = {
                                couponInput = it
                                couponError = false
                            },
                            placeholder = { Text("Cupón (ej. TECHPERU)", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TechPrimary
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val ok = viewModel.applyCoupon(couponInput)
                                couponError = !ok
                                if (ok) couponInput = ""
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = TechSecondary)
                        ) {
                            Text("Aplicar", fontWeight = FontWeight.Bold)
                        }
                    }

                    if (couponError) {
                        Text(
                            text = "Cupón inválido. Prueba con TECHPERU para 10% OFF.",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    appliedCoupon?.let { coupon ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "✓ Cupón $coupon activado ($couponDiscountPercent% desc.)",
                                color = Color(0xFF10B981),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Quitar",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.clickable { viewModel.removeCoupon() }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Summary Totals
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal:", style = MaterialTheme.typography.bodySmall)
                        Text("S/. ${String.format(Locale.US, "%,.2f", subtotal)}", style = MaterialTheme.typography.bodySmall)
                    }
                    if (discount > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Descuento Cupón ($couponDiscountPercent%):", style = MaterialTheme.typography.bodySmall, color = Color(0xFF10B981))
                            Text("- S/. ${String.format(Locale.US, "%,.2f", discount)}", style = MaterialTheme.typography.bodySmall, color = Color(0xFF10B981))
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Costo de Envío:", style = MaterialTheme.typography.bodySmall)
                        Text(if (shipping > 0) "S/. ${String.format(Locale.US, "%,.2f", shipping)}" else "GRATIS", style = MaterialTheme.typography.bodySmall)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Total a Pagar:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "S/. ${String.format(Locale.US, "%,.2f", total)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = TechPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Proceed to Payment Gateway Button
            Button(
                onClick = onProceedToCheckout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("cart_proceed_to_checkout_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TechPrimary)
            ) {
                Icon(
                    imageVector = Icons.Default.CreditCard,
                    contentDescription = null,
                    tint = Color(0xFF00363D)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "CONTINUAR A PASARELA DE PAGOS",
                    color = Color(0xFF00363D),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.brand,
                    style = MaterialTheme.typography.labelSmall,
                    color = TechPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "S/. ${String.format(Locale.US, "%,.2f", item.product.priceSoles)} c/u",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Quantity adjusters
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                    .padding(2.dp)
            ) {
                IconButton(onClick = onDecrease, modifier = Modifier.size(30.dp)) {
                    Icon(Icons.Default.Remove, contentDescription = "Menos", modifier = Modifier.size(16.dp))
                }
                Text(
                    text = "${item.quantity}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
                IconButton(onClick = onIncrease, modifier = Modifier.size(30.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Más", modifier = Modifier.size(16.dp))
                }
            }

            IconButton(onClick = onRemove, modifier = Modifier.size(36.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun CheckoutPaymentGatewayView(
    viewModel: StoreViewModel,
    onBackToCart: () -> Unit,
    onClose: () -> Unit
) {
    val checkoutData by viewModel.checkoutData.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()
    val couponDiscountPercent by viewModel.couponDiscountPercent.collectAsState()
    val scrollState = rememberScrollState()

    val subtotal = cartItems.sumOf { it.totalPrice }
    val discount = subtotal * (couponDiscountPercent / 100.0)
    val shipping = checkoutData.shippingMethod.costSoles
    val total = (subtotal - discount + shipping).coerceAtLeast(0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackToCart) {
                    Icon(Icons.Default.Close, contentDescription = "Volver al carrito")
                }
                Text(
                    text = "Pasarela de Pagos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFF10B981),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Pago Seguro SSL",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF10B981),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            // Customer Details Form
            Text(
                text = "1. Datos del Comprador (Perú)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TechPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = checkoutData.fullName,
                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(fullName = value) } },
                label = { Text("Nombre y Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = checkoutData.documentNumber,
                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(documentNumber = value) } },
                    label = { Text("DNI / RUC") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = checkoutData.phone,
                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(phone = value) } },
                    label = { Text("Teléfono / WhatsApp") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = checkoutData.email,
                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(email = value) } },
                label = { Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                value = checkoutData.address,
                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(address = value) } },
                label = { Text("Dirección de Envío / Entrega") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = checkoutData.district,
                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(district = value) } },
                    label = { Text("Distrito") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = checkoutData.city,
                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(city = value) } },
                    label = { Text("Ciudad / Región") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Payment Methods Selector
            Text(
                text = "2. Selecciona Método de Pago",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TechPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))

            PaymentType.values().forEach { method ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            viewModel.updateCheckoutData { it.copy(paymentType = method) }
                        },
                    shape = RoundedCornerShape(12.dp),
                    color = if (checkoutData.paymentType == method)
                        TechPrimary.copy(alpha = 0.12f)
                    else MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (checkoutData.paymentType == method) TechPrimary else Color.Transparent
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = checkoutData.paymentType == method,
                            onClick = { viewModel.updateCheckoutData { it.copy(paymentType = method) } },
                            colors = RadioButtonDefaults.colors(selectedColor = TechPrimary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = method.displayName,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = method.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Specific Payment Type Details Form
            when (checkoutData.paymentType) {
                PaymentType.YAPE_PLIN -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E1065))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "📱 Paga con Yape o Plin al 953499046",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Titular: Gabriel Alonzo (TechPerú Store)",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFE9D5FF)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            // QR Preview Box
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.White, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.QrCode,
                                        contentDescription = null,
                                        tint = Color(0xFF7000FF),
                                        modifier = Modifier.size(72.dp)
                                    )
                                    Text(
                                        text = "QR YAPE / PLIN",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        color = Color.Black
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = checkoutData.operationNumber,
                                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(operationNumber = value) } },
                                label = { Text("Número de Operación de Yape / Plin") },
                                placeholder = { Text("Ej. 849201") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFFA855F7),
                                    unfocusedBorderColor = Color(0xFF9333EA)
                                )
                            )
                        }
                    }
                }
                PaymentType.TARJETA -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Credit Card Visual
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(Color(0xFF0F172A), Color(0xFF1E293B), Color(0xFF0284C7))
                                        )
                                    )
                                    .padding(14.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("TechPerú Gold Card", color = Color.White, fontWeight = FontWeight.Bold)
                                        Text("VISA / MC", color = Color(0xFF38BDF8), fontWeight = FontWeight.Black)
                                    }

                                    Text(
                                        text = if (checkoutData.cardNumber.isNotEmpty()) checkoutData.cardNumber else "•••• •••• •••• ••••",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = FontFamily.Monospace
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = if (checkoutData.cardHolder.isNotEmpty()) checkoutData.cardHolder.uppercase() else "TITULAR DE LA TARJETA",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                        Text(
                                            text = if (checkoutData.cardExpiry.isNotEmpty()) checkoutData.cardExpiry else "MM/AA",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = checkoutData.cardNumber,
                                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(cardNumber = value) } },
                                label = { Text("Número de Tarjeta (16 dígitos)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedTextField(
                                    value = checkoutData.cardExpiry,
                                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(cardExpiry = value) } },
                                    label = { Text("Vencimiento (MM/AA)") },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = checkoutData.cardCvv,
                                    onValueChange = { value -> viewModel.updateCheckoutData { it.copy(cardCvv = value) } },
                                    label = { Text("CVV") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                                    modifier = Modifier.weight(1f),
                                    singleLine = true
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))

                            OutlinedTextField(
                                value = checkoutData.cardHolder,
                                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(cardHolder = value) } },
                                label = { Text("Nombre como figura en la tarjeta") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                }
                PaymentType.TRANSFERENCIA -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Cuentas Bancarias Oficiales:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("• BCP Soles: 194-98412847-0-12 (CCI: 0021940098412847012)")
                            Text("• BBVA Soles: 0011-0175-0200849182")
                            Text("• Interbank Soles: 200-3001847192")
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = checkoutData.operationNumber,
                                onValueChange = { value -> viewModel.updateCheckoutData { it.copy(operationNumber = value) } },
                                label = { Text("Nro. de Operación Bancaria") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                }
                PaymentType.CONTRA_ENTREGA -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Pago al Recibir el Pedido", fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "El repartidor llevará POS para tarjeta y cambio en efectivo si lo requieres. Aplica únicamente para Lima Metropolitana.",
                                color = Color(0xFFD1FAE5),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Confirm & Pay CTA
        Button(
            onClick = {
                viewModel.confirmOrder()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("checkout_confirm_order_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TechPrimary)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF00363D)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "PAGAR S/. ${String.format(Locale.US, "%,.2f", total)}",
                color = Color(0xFF00363D),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun OrderConfirmationView(
    order: OrderConfirmation,
    viewModel: StoreViewModel,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Success Icon
            Surface(
                shape = CircleShape,
                color = Color(0xFF10B981).copy(alpha = 0.2f),
                modifier = Modifier.size(72.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(46.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "¡Pedido Confirmado con Éxito!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Tu pago ha sido procesado por nuestra pasarela digital.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Digital Ticket Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, TechPrimary.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Ticket Digital TechPerú",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = TechPrimary
                        )
                        Text(
                            text = order.orderId,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fecha: ${order.date}", style = MaterialTheme.typography.bodySmall)
                    Text("Cliente: ${order.customer.fullName}", style = MaterialTheme.typography.bodySmall)
                    Text("DNI/RUC: ${order.customer.documentNumber}", style = MaterialTheme.typography.bodySmall)
                    Text("Destino: ${order.customer.address}, ${order.customer.district} - ${order.customer.city}", style = MaterialTheme.typography.bodySmall)
                    Text("Método: ${order.customer.paymentType.displayName}", style = MaterialTheme.typography.bodySmall)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    )

                    Text(
                        text = "Productos Comprados:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )

                    order.items.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${item.quantity}x ${item.product.name}",
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "S/. ${String.format(Locale.US, "%,.2f", item.totalPrice)}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal:", style = MaterialTheme.typography.bodySmall)
                        Text("S/. ${String.format(Locale.US, "%,.2f", order.subtotal)}", style = MaterialTheme.typography.bodySmall)
                    }

                    if (order.discount > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Descuento:", style = MaterialTheme.typography.bodySmall, color = Color(0xFF10B981))
                            Text("- S/. ${String.format(Locale.US, "%,.2f", order.discount)}", style = MaterialTheme.typography.bodySmall, color = Color(0xFF10B981))
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Envío:", style = MaterialTheme.typography.bodySmall)
                        Text("S/. ${String.format(Locale.US, "%,.2f", order.shippingCost)}", style = MaterialTheme.typography.bodySmall)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Total Pagado:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(
                            "S/. ${String.format(Locale.US, "%,.2f", order.total)}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = TechPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // WhatsApp notification button
        Button(
            onClick = {
                val whatsappMsg = "¡Hola TechPerú! 👋 Acabo de realizar el pedido ${order.orderId} por S/. ${String.format(Locale.US, "%,.2f", order.total)}. Mi nombre es ${order.customer.fullName} y deseo confirmar el despacho."
                viewModel.launchWhatsApp(context, whatsappMsg)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("order_send_to_whatsapp_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_whatsapp),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Enviar comprobante al WhatsApp (+51 953 499 046)", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onClose,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .testTag("order_back_to_store_button"),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Volver a la Tienda")
        }
    }
}
