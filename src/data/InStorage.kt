package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object InStorages : IntIdTable() {
    val storageId = reference("storageId", Storages)
    val productId = reference("productId", Products)
    val quantity = integer("quantity")
}

class InStorageEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<InStorageEntity>(InStorages)

    var storageId by StorageEntity referencedOn InStorages.storageId
    var productId by ProductEntity referencedOn InStorages.productId
    var quantity by InStorages.quantity

    override fun toString(): String = "InStorage(${storageId.storageName}, ${productId.productName}, $quantity)"

    fun toInStorage() = InStorage(id.value, storageId.id.value, productId.id.value, quantity)
}

data class InStorage(
    val InStoragesId: Int,
    val storageId: Int,
    val productId: Int,
    val quantity: Int
)