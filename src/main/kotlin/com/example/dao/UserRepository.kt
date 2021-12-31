package com.example.dao

import com.example.model.User
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepository(db: CoroutineDatabase) {

    private val userCollection: CoroutineCollection<User> = db.getCollection()
    suspend fun insertUser(user: User) {
        userCollection.insertOne(User(userName = user.userName))
    }

    suspend fun getAllUsers(): List<User> {
        return userCollection.find(User::userName eq "Avinash").toList()
    }

    suspend fun deleteUser(user: User) {
        userCollection.deleteOneById(user.userId)
    }
}