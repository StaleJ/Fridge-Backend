package com.example.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Fridges : IntIdTable() {
    val name = varchar("name", 255)
    val item = reference("item", Items)
}

class Fridge(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Fridge>(Fridges)

    var name by Fridges.name
    var item by Item referencedOn Fridges.item
}