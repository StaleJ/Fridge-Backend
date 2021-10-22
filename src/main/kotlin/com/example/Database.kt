package com.example

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    Database.connect("jdbc:mysql://localhost:3306/myDb", driver = "com.mysql.jdbc.Driver",
        user = "root", password = "test")

    transaction {
        SchemaUtils.create(Items)

        Items.insert {
            it[Items.name] = "Brunost"
        }

    }
}