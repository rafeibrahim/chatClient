/*
 - author: rafei
 - date: 08/10/2019
 - Input Stream handler class takes care of continuously listening to inputStream from Server using Thread. Based on command portion of chatMessage object received
 - from it makes decision on how to handle incoming chatMessage objects
 */
package com.example.chatclientrib.chatClientLogic

import android.util.Log
import kotlinx.io.InputStream
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonParsingException
import java.util.*
import kotlinx.serialization.list
import kotlinx.serialization.set
import kotlin.NoSuchElementException

class InputStreamHandler(private val inputStreamObject: InputStream) {
    private val commandReceiver = Scanner(inputStreamObject)

    fun startListening() {
        Thread(Runnable {
            while (true) {

                try {
                    val commandAsJsonString = commandReceiver.nextLine()
                    val chatMessageCommand =
                        JSON.parse(ChatMessage.serializer(), commandAsJsonString)

                    if (chatMessageCommand.getCommand().equals("message")) {
                        //adds incoming msgs from server to incomingMessagesList in object ChatClientDatabase
                        ChatClientDatabase.insertToIncomingChatMessagesList(chatMessageCommand)

                    } else if (chatMessageCommand.getCommand().equals("username")) {
                        //this is acknowledgement message from server that username is successfully registered. sets ChatClientUser to registered username
                        ChatClientController.setUserName(chatMessageCommand.getUsername())

                    } else if (chatMessageCommand.getCommand().equals("history")) {
                        //this is historyChatMessage received in response to query by chatclient to getChatHistory
                        //chatServer sends Jsonified whole list<ChatMessage> data structure in the message portion of chatMessage object
                        val chatHistoryListJson = chatMessageCommand.getMessage()
                        val chatHistoryList =
                            JSON.parse(ChatMessage.serializer().list, chatHistoryListJson)
                        ChatClientDatabase.setChatHistory(chatHistoryList.toMutableList())

                    } else if (chatMessageCommand.getCommand().equals("users")) {
                        //this is usersChatMessage received in response to query by chatclient to getChatUsers
                        //chatServer sends Jsonified whole set<ChatMessage> data structure in the message portion of chatMessage object
                        val usersSetJson = chatMessageCommand.getMessage()
                        val usersSet = JSON.parse(ChatUser.serializer().set, usersSetJson)
                        ChatClientDatabase.setChatUserSet(usersSet.toMutableSet())
                    }
                } catch (e: JsonParsingException) {
                    Log.d("testing", e.toString())
                } catch (e: NoSuchElementException) {
                    Log.d("testing", e.toString())
                }
            }
        }).start()
    }
}