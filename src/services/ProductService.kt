package at.stefangaller.services

import at.stefangaller.data.Product
import at.stefangaller.data.ProductEntity
import org.jetbrains.exposed.sql.transactions.transaction

class ProductService {

    fun getAllProduct(): Iterable<Product> = transaction {
        ProductEntity.all().map(ProductEntity::toProduct)
    }

    fun getProduct(productId: Int) = transaction {
        ProductEntity[productId]
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