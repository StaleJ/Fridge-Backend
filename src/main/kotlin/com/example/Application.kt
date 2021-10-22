package com.example

import com.example.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun main() {


    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {
        configureDatabase()
        configureRouting()

    }.start(wait = true)


}
