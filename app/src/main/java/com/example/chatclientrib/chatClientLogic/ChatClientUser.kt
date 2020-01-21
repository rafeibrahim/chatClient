/*
 - author: rafei
 - date: 08/10/2019
 - ChatClientUser object class keeps registered username of this chatClient.
 */
package com.example.chatclientrib.chatClientLogic

object ChatClientUser {
    private lateinit var username: String

    fun setUsername(inputUsername: String) {
        this.username = inputUsername
    }

    fun getUsername(): String {
        return this.username
    }

    fun usernameInitialized(): Boolean {
        return ::username.isInitialized
    }
}