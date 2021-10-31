package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object InStorages : IntIdTable() {
    val storage = reference("storageId", Storages)
    val product = reference("productId", Products)
    val quantity = integer("quantity")
}

class InStorageEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<InStorageEntity>(InStorages)

    var storage by StorageEntity referencedOn InStorages.storage
    var product by ProductEntity referencedOn InStorages.product
    var quantity by InStorages.quantity

    override fun toString(): String = "InStorage(${storage.storageName}, ${product.productName}, $quantity)"

    fun toInStorage() = InStorage(id.value, storage, product, quantity)
}

data class InStorage(
    val InStoragesId: Int,
    val storage: StorageEntity,
    val product: ProductEntity,
    val quantity: Int
)