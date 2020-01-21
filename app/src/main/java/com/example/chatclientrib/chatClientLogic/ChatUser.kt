/*
 - author: rafei
 - date: 08/10/2019
 - ChatUser class is created to parse sent Set<ChatUser> datastructure received from ChatServer. It is the replica of ChatUser class in ChatServer.
 */
package com.example.chatclientrib.chatClientLogic

import kotlinx.serialization.Serializable

@Serializable
class ChatUser(private val userName: String) {
    private var messageCount = 0

    //increment message count in ChatUser
    fun addMessageCount() {
        messageCount++
    }

    fun getMessageCount(): Int {
        return this.messageCount
    }

    fun getUserName(): String {
        return this.userName
    }

    override fun toString(): String {
        return "Username -> " + this.userName + "\nMessages Sent = " + this.messageCount
    }

    //equals and hashCode methods implemented to compare ChatUser objects
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ChatUser
        if (userName != other.userName) return false
        return true
    }

    override fun hashCode(): Int {
        return userName.hashCode()
    }
}