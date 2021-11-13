package at.stefangaller.services

import at.stefangaller.data.InStorage
import at.stefangaller.data.InStorageEntity
import org.jetbrains.exposed.sql.transactions.transaction

class InStoragesService {

    fun getAllInStorages(): Iterable<InStorage> = transaction {
        InStorageEntity.all().map(InStorageEntity::toInStorage)
    }

    fun addProductToStorage(inStorage: InStorage) = transaction {
        // TODO: Make update not new if the product is in storage
        InStorageEntity.new {
            this.product = inStorage.product
            this.storage = inStorage.storage
            this.quantity = inStorage.quantity
            this.expiredDate = inStorage.expiredDate
        }

    }

}