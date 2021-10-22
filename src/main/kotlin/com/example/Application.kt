package com.example

import com.example.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Items : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Item_ID")
}

fun main() {


    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureDatabase()

    }.start(wait = true)


}
