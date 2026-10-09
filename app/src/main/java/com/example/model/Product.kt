package com.example.model

enum class Category(val displayName: String, val iconRes: String) {
    ALL("Todos", "all"),
    MONITORES("Monitores", "monitor"),
    PROCESADORES("Procesadores", "cpu"),
    TECLADOS("Teclados", "keyboard"),
    MOUSE("Mouse", "mouse"),
    TARJETAS_VIDEO("Tarjetas de Video", "gpu"),
    COMPONENTES("RAM y SSD", "ram_ssd")
}

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: Category,
    val priceSoles: Double,
    val originalPriceSoles: Double? = null,
    val rating: Float,
    val reviewCount: Int,
    val description: String,
    val specs: Map<String, String>,
    val highlights: List<String>,
    val stock: Int,
    val isFeatured: Boolean = false,
    val warrantyMonths: Int = 12,
    val tags: List<String> = emptyList()
) {
    val discountPercent: Int?
        get() = originalPriceSoles?.let {
            if (it > priceSoles) (((it - priceSoles) / it) * 100).toInt() else null
        }
}

data class CartItem(
    val product: Product,
    val quantity: Int = 1
) {
    val totalPrice: Double
        get() = product.priceSoles * quantity
}

enum class ShippingMethod(val displayName: String, val costSoles: Double, val estimatedDelivery: String) {
    EXPRESS_LIMA("Envío Express (Lima Metropolitana)", 15.0, "24 - 48 horas"),
    PROVINCIAS("Envío a Provincias (Olva / Shalom)", 25.0, "2 - 4 días hábiles"),
    TIENDA("Recojo en Tienda (Av. Garcilaso / Wilson)", 0.0, "Inmediato - Mismo día")
}

enum class PaymentType(val displayName: String, val description: String) {
    YAPE_PLIN("Yape / Plin", "Pago instantáneo con QR o número al 953499046"),
    TARJETA("Tarjeta de Crédito / Débito", "Visa, Mastercard, American Express"),
    TRANSFERENCIA("Transferencia BCP / BBVA / Interbank", "Cuentas bancarias directas y CCI"),
    CONTRA_ENTREGA("Pago Contra Entrega", "Paga en efectivo o POS al recibir tu pedido")
}

data class CheckoutData(
    val fullName: String = "",
    val documentNumber: String = "", // DNI / RUC
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val city: String = "Lima",
    val district: String = "",
    val shippingMethod: ShippingMethod = ShippingMethod.EXPRESS_LIMA,
    val paymentType: PaymentType = PaymentType.YAPE_PLIN,
    val couponCode: String = "",
    val operationNumber: String = "", // Para Yape / Plin / Transferencia
    val cardNumber: String = "",
    val cardExpiry: String = "",
    val cardCvv: String = "",
    val cardHolder: String = ""
)

data class OrderConfirmation(
    val orderId: String,
    val date: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val discount: Double,
    val shippingCost: Double,
    val total: Double,
    val customer: CheckoutData
)
