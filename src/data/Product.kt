package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable() {
    val productName = varchar("productName", 255)
}

class ProductEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductEntity>(Products)

    var productName by Products.productName

    override fun toString(): String = "Product($productName)"

    fun toProduct() = Product(id.value, productName)
}

data class Product(
    val productId: Int,
    val productName: String
)
