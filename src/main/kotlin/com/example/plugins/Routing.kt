package com.example.plugins

import com.example.dao.MessageRepository
import com.example.dao.UserRepository
import com.example.routing.messageRouting
import com.example.routing.userRouting
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    userRouting()


    messageRouting()


}
