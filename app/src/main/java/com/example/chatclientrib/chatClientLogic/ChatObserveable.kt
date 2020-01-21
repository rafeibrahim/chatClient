/*
 - author: rafei
 - date: 08/10/2019
 - ChatObserveable interface implemented as part of Observer Pattern.
 */
package com.example.chatclientrib.chatClientLogic

interface ChatObserveable {
    fun registerChatObserver(chatObserverObject: ChatObserver)
    fun deregisterChatObserver(chatObserverObject: ChatObserver)
    fun updateChatObservers()
}