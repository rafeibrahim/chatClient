/*
 - author: rafei
 - date: 08/10/2019
 - ChatMessage class is used to parse incoming Json stringified ChatMessage objects from chatServer. It is the replica of ChatMessage Class in ChatServer
 */
package com.example.chatclientrib.chatClientLogic

import kotlinx.serialization.Serializable

@Serializable
class ChatMessage(
    private val command: String,
    private val message: String,
    private val dateAndTime: String,
    private val username: String
) {

    override fun toString(): String {
        return username + "\n" + this.message + "\n" + this.dateAndTime
    }

    fun getCommand(): String {
        return this.command
    }

    fun getMessage(): String {
        return this.message
    }

    fun getUsername(): String {
        return this.username
    }
}
