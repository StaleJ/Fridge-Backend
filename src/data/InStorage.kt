package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime


object InStorages : IntIdTable() {
    val storage = reference("storageId", Storages)
    val product = reference("productId", Products)
    val quantity = integer("quantity")
    val expiredDate = datetime("expiredDate")
    override val primaryKey = PrimaryKey(storage, product, name = "PK_InStorage")
}

class InStorageEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<InStorageEntity>(InStorages)

    var storage by StorageEntity referencedOn InStorages.storage
    var product by ProductEntity referencedOn InStorages.product
    var quantity by InStorages.quantity
    var expiredDate by InStorages.expiredDate

    override fun toString(): String = "InStorage(${storage.storageName}, ${product.productName}, $quantity, $expiredDate)"

    fun toInStorage() = InStorage(storage, product, quantity, expiredDate)
}

data class InStorage(
    val storage: StorageEntity,
    val product: ProductEntity,
    val quantity: Int,
    val expiredDate: LocalDateTime
)