package at.stefangaller.services

import at.stefangaller.data.Storage
import at.stefangaller.data.StorageEntity
import org.jetbrains.exposed.sql.transactions.transaction

class StorageService {

    fun getAllStorages(): Iterable<Storage> = transaction {
        StorageEntity.all().map(StorageEntity::toStorage)
    }

    fun addStorage(storage: Storage) = transaction {
        StorageEntity.new {
            this.storageName = storage.storageName
        }
    }

    fun deleteStorage(storageId: Int) = transaction {
        StorageEntity[storageId].delete()
    }
}