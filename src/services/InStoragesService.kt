package at.stefangaller.services

import at.stefangaller.data.InStorage
import at.stefangaller.data.InStorageEntity
import org.jetbrains.exposed.sql.transactions.transaction

class InStoragesService {

    fun getAllInStorages(): Iterable<InStorage> = transaction {
        InStorageEntity.all().map(InStorageEntity::toInStorage)
    }

    fun addProductToStorage(inStorage: InStorage) = transaction {
        InStorageEntity.new {
            this.product = inStorage.product
            this.storage = inStorage.storage
            this.quantity = inStorage.quantity
        }

    }

}