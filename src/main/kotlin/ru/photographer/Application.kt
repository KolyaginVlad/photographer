package ru.photographer

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.photographer.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
