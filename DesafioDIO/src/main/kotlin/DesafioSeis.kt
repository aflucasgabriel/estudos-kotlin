class Product(val name: String, val price: Double, val quantity: Int)

class CustomOrder private constructor(
    val customerName: String,
    val products: List<Product>,
    val total: Double,
    val deliveryAddress: String
) {
    /** Classe interna para "linkar" o Bulder com a classe CustomOrder **/
    class Builder {
        private var customerName: String = ""
        private var products: MutableList<Product> = mutableListOf()
        private var deliveryAddress: String = ""


        fun setCustomerName(name: String) = apply { customerName = name }
        fun addProduct(product: Product) = apply { products.add(product) }
        fun setDeliveryAddress(address: String) = apply { deliveryAddress = address }

        fun build(): CustomOrder {
            val total = products.sumByDouble { it.price * it.quantity }

            return CustomOrder(customerName, products.toList(), total, deliveryAddress)

            //TODO("Implemente a lógica para calcular o Total do Pedido (a partir dos dados de Produtos).")
            //TODO("Instancie corretamente um CustomOrder, consolidando o Builder!")
        }
    }

    fun printReceipt() {
        println("${this.customerName}")
        this.products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.name} | ${product.price} | ${product.quantity}")
        }
        println("Total: ${this.total}")
        println("End: ${this.deliveryAddress}")
    }
}

fun main() {
    print("Nome do consumidor: ")
    val customerName = readLine() ?: ""

    val orderBuilder = CustomOrder.Builder().setCustomerName(customerName)

    print("Quantos produtos deseja comprar?: ")
    val numProducts = readLine()?.toIntOrNull() ?: 0
    for (i in 1..numProducts) {
        print("Nome do produto: ")
        val productName = readLine() ?: ""
        print("Preço do produto: ")
        val productPrice = readLine()?.toDoubleOrNull() ?: 0.0
        print("Quantidade de produtos: ")
        val productQuantity = readLine()?.toIntOrNull() ?: 0

        orderBuilder.addProduct(Product(productName, productPrice, productQuantity))
    }

    print("Endereço de entrega: ")
    val deliveryAddress = readLine() ?: ""

    val customOrder = orderBuilder.setDeliveryAddress(deliveryAddress).build()

    println("================Recibo================")
    customOrder.printReceipt()
}