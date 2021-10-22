package com.example

import com.example.models.Item
import com.example.plugins.configureRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Items : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Item_ID")

    fun toItem(row: ResultRow): Item =
        Item(
            id = row[Items.id],
            name = row[Items.name]
        )
}

fun main() {


    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {



            configureDatabase()
            configureRouting()


    }.start(wait = true)


}
