package com.example.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Items : IntIdTable() {
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id, name = "PK_Item_ID")

    fun toItem(row: ResultRow): Item =
        Item()

}

class Item(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Item>(Items)

    var name by Items.name
}