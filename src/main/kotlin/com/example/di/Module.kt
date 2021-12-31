package com.example.di

import com.example.dao.MessageRepository
import com.example.dao.UserRepository
import com.example.model.Message
import com.example.model.User
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val myModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase("my_chat")
    }

    single {
        UserRepository(get())
    }


    single {
        MessageRepository(get())
    }

}