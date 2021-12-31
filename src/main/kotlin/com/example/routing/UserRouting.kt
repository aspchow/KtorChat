package com.example.routing

import com.example.dao.UserRepository
import com.example.model.User
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.userRouting() {

   val userRepository: UserRepository by inject()


    routing {

        get("/users") {
            call.respond(userRepository.getAllUsers())
        }

        post("/addUser") {
            println("called the addUser avinash successfully")
            val user = call.receive<User>()
            userRepository.insertUser(user)
            call.respond("Successfully Added")
        }
    }
}