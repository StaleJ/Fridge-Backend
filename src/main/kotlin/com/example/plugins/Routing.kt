package com.example.plugins

import com.example.models.Items
import com.example.models.Item
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {

    install(Routing) {
        route("/items") {
            get("/") {
                val items = transaction {
                    Items.selectAll().map { Items.toItem(it) }
                }
                call.respond(items)
                println(items[0].name)
            }

            post("/") {
                val item = call.receive<Item>()
                transaction {
                    Items.insert {
                        it[Items.name] = item.name
                    }
                }
                call.respond(item)
            }
        }
    }
}
