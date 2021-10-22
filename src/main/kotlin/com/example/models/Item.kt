package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Items : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Item_ID")

    fun toItem(row: ResultRow): Item =
        Item(
            id = row[id],
            name = row[name]
        )
}

data class Item(val id: Int? = null, val name: String)