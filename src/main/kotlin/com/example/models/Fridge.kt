package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Fridge : Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    val item: Column<Item> = TODO()

}