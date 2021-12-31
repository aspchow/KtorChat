package com.example

import com.example.di.myModule
import com.example.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    startKoin {
        modules(myModule)
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

        configureSockets()
        configureRouting()
        configureSerialization()
        configureMonitoring()
        configureSecurity()
    }.start(wait = true)
}
