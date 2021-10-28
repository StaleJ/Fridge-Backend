package at.stefangaller.services

import at.stefangaller.data.Product
import at.stefangaller.data.ProductEntity
import io.ktor.http.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class ProductService {

    fun getAllProduct(): Iterable<Product> = transaction {
        ProductEntity.all().map(ProductEntity::toProduct)
    }

    fun addProduct(product: Product) = transaction {
        ProductEntity.new {
            this.productName = product.productName
            this.expiredDate = product.expiredDate
        }
    }

    fun deleteProduct(productId: Int) = transaction {
        ProductEntity[productId].delete()
    }
}