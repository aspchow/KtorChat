package com.example.dao

import com.example.model.Message
import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageRepository(db: CoroutineDatabase) {

    private val messageCollection: CoroutineCollection<Message> = db.getCollection()

    suspend fun getMessages(): List<Message> {
        return messageCollection.find().toList()
    }

    suspend fun insertMessage(message: Message) : InsertOneResult {
      return messageCollection.insertOne(message)
    }
}