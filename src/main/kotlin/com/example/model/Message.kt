package com.example.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val messageId: String = ObjectId().toString(),
    val messageText: String,
    val userName: String,
    val createdTime: Long=  System.currentTimeMillis()
)