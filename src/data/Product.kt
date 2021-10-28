package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.date
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.sql.Date
import java.time.LocalDate

object Products : IntIdTable() {
    val productName = varchar("productName", 255)
    val expiredDate = date("expiredDate")
}

class ProductEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductEntity>(Products)

    var productName by Products.productName
    var expiredDate by Products.expiredDate

    override fun toString(): String = "Product($productName, $expiredDate)"

    fun toProduct() = Product(id.value, productName, expiredDate)
}

data class Product(
    val productId: Int,
    val productName: String,
    val expiredDate: LocalDate
)
