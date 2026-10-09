package com.example.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.data.ProductRepository
import com.example.model.CartItem
import com.example.model.Category
import com.example.model.CheckoutData
import com.example.model.OrderConfirmation
import com.example.model.PaymentType
import com.example.model.Product
import com.example.model.ShippingMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class AppScreen {
    WELCOME,
    CATALOG
}

enum class PriceSort(val label: String) {
    RELEVANCE("Destacados"),
    PRICE_ASC("Menor precio"),
    PRICE_DESC("Mayor precio"),
    RATING("Mejor valorados")
}

class StoreViewModel : ViewModel() {

    companion object {
        const val WHATSAPP_PHONE = "+51953499046"
        const val WHATSAPP_RAW_NUMBER = "51953499046"
        const val STORE_NAME = "TechPerú Store"
    }

    private val _currentScreen = MutableStateFlow(AppScreen.WELCOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(Category.ALL)
    val selectedCategory: StateFlow<Category> = _selectedCategory.asStateFlow()

    private val _selectedBrand = MutableStateFlow<String?>(null)
    val selectedBrand: StateFlow<String?> = _selectedBrand.asStateFlow()

    private val _priceSort = MutableStateFlow(PriceSort.RELEVANCE)
    val priceSort: StateFlow<PriceSort> = _priceSort.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _selectedProductDetail = MutableStateFlow<Product?>(null)
    val selectedProductDetail: StateFlow<Product?> = _selectedProductDetail.asStateFlow()

    private val _isCartOpen = MutableStateFlow(false)
    val isCartOpen: StateFlow<Boolean> = _isCartOpen.asStateFlow()

    private val _isCheckoutOpen = MutableStateFlow(false)
    val isCheckoutOpen: StateFlow<Boolean> = _isCheckoutOpen.asStateFlow()

    private val _checkoutData = MutableStateFlow(
        CheckoutData(
            fullName = "Gabriel Alonzo",
            documentNumber = "72914830",
            phone = "953499046",
            email = "gabrielalonzo953@gmail.com",
            address = "Av. Arequipa 1840",
            city = "Lima",
            district = "Miraflores",
            shippingMethod = ShippingMethod.EXPRESS_LIMA,
            paymentType = PaymentType.YAPE_PLIN
        )
    )
    val checkoutData: StateFlow<CheckoutData> = _checkoutData.asStateFlow()

    private val _appliedCoupon = MutableStateFlow<String?>(null)
    val appliedCoupon: StateFlow<String?> = _appliedCoupon.asStateFlow()

    private val _couponDiscountPercent = MutableStateFlow(0)
    val couponDiscountPercent: StateFlow<Int> = _couponDiscountPercent.asStateFlow()

    private val _completedOrder = MutableStateFlow<OrderConfirmation?>(null)
    val completedOrder: StateFlow<OrderConfirmation?> = _completedOrder.asStateFlow()

    private val _isWhatsAppSheetOpen = MutableStateFlow(false)
    val isWhatsAppSheetOpen: StateFlow<Boolean> = _isWhatsAppSheetOpen.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun setSelectedBrand(brand: String?) {
        _selectedBrand.value = brand
    }

    fun setPriceSort(sort: PriceSort) {
        _priceSort.value = sort
    }

    fun openProductDetail(product: Product) {
        _selectedProductDetail.value = product
    }

    fun closeProductDetail() {
        _selectedProductDetail.value = null
    }

    fun openCart() {
        _isCartOpen.value = true
    }

    fun closeCart() {
        _isCartOpen.value = false
    }

    fun openCheckout() {
        _isCartOpen.value = false
        _isCheckoutOpen.value = true
    }

    fun closeCheckout() {
        _isCheckoutOpen.value = false
    }

    fun openWhatsAppSheet() {
        _isWhatsAppSheetOpen.value = true
    }

    fun closeWhatsAppSheet() {
        _isWhatsAppSheetOpen.value = false
    }

    fun addToCart(product: Product, quantity: Int = 1) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }
        if (index != -1) {
            val item = current[index]
            current[index] = item.copy(quantity = item.quantity + quantity)
        } else {
            current.add(CartItem(product, quantity))
        }
        _cartItems.value = current
    }

    fun updateCartQuantity(productId: String, delta: Int) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            val item = current[index]
            val newQty = item.quantity + delta
            if (newQty <= 0) {
                current.removeAt(index)
            } else {
                current[index] = item.copy(quantity = newQty)
            }
            _cartItems.value = current
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.value = _cartItems.value.filter { it.product.id != productId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun applyCoupon(code: String): Boolean {
        val normalized = code.trim().uppercase()
        return if (normalized == "TECHPERU" || normalized == "CYBER10" || normalized == "GAMER10") {
            _appliedCoupon.value = normalized
            _couponDiscountPercent.value = 10
            true
        } else if (normalized == "WELCOME20") {
            _appliedCoupon.value = normalized
            _couponDiscountPercent.value = 20
            true
        } else {
            false
        }
    }

    fun removeCoupon() {
        _appliedCoupon.value = null
        _couponDiscountPercent.value = 0
    }

    fun updateCheckoutData(update: (CheckoutData) -> CheckoutData) {
        _checkoutData.value = update(_checkoutData.value)
    }

    fun confirmOrder(): OrderConfirmation {
        val items = _cartItems.value
        val subtotal = items.sumOf { it.totalPrice }
        val discount = subtotal * (_couponDiscountPercent.value / 100.0)
        val shipping = _checkoutData.value.shippingMethod.costSoles
        val total = (subtotal - discount + shipping).coerceAtLeast(0.0)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val order = OrderConfirmation(
            orderId = "TP-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}-${(1000..9999).random()}",
            date = dateFormat.format(Date()),
            items = items,
            subtotal = subtotal,
            discount = discount,
            shippingCost = shipping,
            total = total,
            customer = _checkoutData.value
        )

        _completedOrder.value = order
        _cartItems.value = emptyList()
        _isCheckoutOpen.value = false
        return order
    }

    fun resetCompletedOrder() {
        _completedOrder.value = null
    }

    fun getFilteredProducts(): List<Product> {
        var list = ProductRepository.searchProducts(_searchQuery.value, _selectedCategory.value)

        _selectedBrand.value?.let { brand ->
            list = list.filter { it.brand.contains(brand, ignoreCase = true) }
        }

        return when (_priceSort.value) {
            PriceSort.RELEVANCE -> list
            PriceSort.PRICE_ASC -> list.sortedBy { it.priceSoles }
            PriceSort.PRICE_DESC -> list.sortedByDescending { it.priceSoles }
            PriceSort.RATING -> list.sortedByDescending { it.rating }
        }
    }

    fun getAvailableBrands(): List<String> {
        return ProductRepository.products
            .map { it.brand }
            .distinct()
            .sorted()
    }

    fun launchWhatsApp(context: Context, customMessage: String? = null) {
        val defaultText = "¡Hola TechPerú Store! 👋 Deseo realizar una consulta sobre sus productos gamer y componentes de PC."
        val text = customMessage ?: defaultText
        val encodedText = Uri.encode(text)
        val url = "https://api.whatsapp.com/send?phone=$WHATSAPP_RAW_NUMBER&text=$encodedText"

        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Si WhatsApp no está disponible, abrir navegador
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(browserIntent)
            } catch (ex: Exception) {
                Toast.makeText(context, "Contacta al WhatsApp: $WHATSAPP_PHONE", Toast.LENGTH_LONG).show()
            }
        }
    }
}
