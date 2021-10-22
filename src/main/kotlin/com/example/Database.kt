package com.example

import com.example.models.Items
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.configureDatabase() {
    install(ContentNegotiation) {
        jackson {
        }

    }
    Database.connect(
        "jdbc:mysql://localhost:3306/myDb", driver = "com.mysql.jdbc.Driver",
        user = "root", password = "test"
    )

    transaction {
        SchemaUtils.create(Items)
    }


}