package com.example.routing

import com.example.dao.MessageRepository
import com.example.model.Message
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.lang.Exception
import java.util.concurrent.ConcurrentLinkedDeque


fun Application.messageRouting() {

    val users = ConcurrentLinkedDeque<DefaultWebSocketSession>()
    val messageRepository: MessageRepository by inject()

    routing {

        get("/messages") {
            call.respond(messageRepository.getMessages())
        }

        post("/addMessage") {
            val message = call.receive<Message>()
            messageRepository.insertMessage(message = message.copy(createdTime = System.currentTimeMillis()))
            call.respond("Successfully Added")
        }

        webSocket("/chat") {
            if (users.contains(this))
                throw Exception("User Already Existing")
            users.add(this)
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        val message = Json { ignoreUnknownKeys = true }.decodeFromString<Message>(text)
                        messageRepository.insertMessage(message)
                        val resultText = Json.encodeToString(message)
                        users.forEach { session ->
                            try {
                                session.send(Frame.Text(resultText))
                            }catch (exception : Exception){
                                users.remove(session)
                            }
                        }
                    }
                }
            }
        }
    }
}