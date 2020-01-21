/*
 - author: rafei
 - date: 08/10/2019
 - ChatClientDatabase object class keeps different datastructures to store incoming ChatMessage objects, set of active ChatUsers, list of chatHistory and set of
 - chatObservers registered with this object class. This class is designated ChatObserveable as part of ObserverPattern implementation.
 */
package com.example.chatclientrib.chatClientLogic

import android.util.Log

object ChatClientDatabase : ChatObserveable {

    private val chatHistoryList = mutableListOf<ChatMessage>()
    private val chatUserSet = mutableSetOf<ChatUser>()
    private val incomingMessagesList = mutableListOf<ChatMessage>()
    private val chatObseversSet = mutableSetOf<ChatObserver>()

    //sets chatHistoryList to new ChatHistoryList received from server
    fun setChatHistory(chatHistorySever: MutableList<ChatMessage>) {
        this.chatHistoryList.clear()
        this.chatHistoryList.addAll(chatHistorySever)
        Log.d("Testing", "chatHistoryList list updated")
    }

    fun getChatHistory(): MutableList<ChatMessage> {
        return this.chatHistoryList
    }

    //sets chatUserSet to new chatUserSet received from server
    fun setChatUserSet(chatUserServer: MutableSet<ChatUser>) {
        this.chatUserSet.clear()
        this.chatUserSet.addAll(chatUserServer)

    }

    fun getChatUsers(): MutableSet<ChatUser> {
        return this.chatUserSet
    }

    fun chatUserExists(username: String): Boolean {
        chatUserSet.forEach {
            if (it.getUserName().equals(username)) {
                return true
            }
        }
        return false
    }

    fun chatUserMessageCount(username: String): Int {
        chatUserSet.forEach {
            if (it.getUserName().equals(username)) {
                return it.getMessageCount()
            }
        }
        return -1
    }

    fun insertToIncomingChatMessagesList(chatMessage: ChatMessage) {
        incomingMessagesList.add(chatMessage)
        this.updateChatObservers()
    }

    fun getIncomingMessagesListSize(): Int {
        return incomingMessagesList.size
    }

    fun getIncomingMessagesList(): MutableList<ChatMessage> {
        return incomingMessagesList
    }

    override fun deregisterChatObserver(chatObserverObject: ChatObserver) {
        this.chatObseversSet.remove(chatObserverObject)
    }

    override fun updateChatObservers() {
        this.chatObseversSet.forEach {
            it.update()
        }
    }

    override fun registerChatObserver(chatObserverObject: ChatObserver) {
        this.chatObseversSet.add(chatObserverObject)
    }
}