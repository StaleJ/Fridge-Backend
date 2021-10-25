package com.example.routes

import io.ktor.routing.*

fun Routing.apiRoute() {
    route("/api/v1") {
        books()
    }
}