package at.stefangaller.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Storages : IntIdTable() {
    val storageName = varchar("storageName", 255)
}

class StorageEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<StorageEntity>(Storages)

    var storageName by Storages.storageName

    override fun toString(): String = "Storage($storageName)"

    fun toStorage() = Storage(id.value, storageName)

}

data class Storage(
    val storageId: Int,
    val storageName: String
)