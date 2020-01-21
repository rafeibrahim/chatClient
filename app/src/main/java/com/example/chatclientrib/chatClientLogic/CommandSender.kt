/*
 - author: rafei
 - date: 08/10/2019
 - CommandSender class takes care of sending chatMessage object to Server. It stringify chatMessage receieved chatMessage object and sends
 - it using outputStream of chatClient
 */
package com.example.chatclientrib.chatClientLogic

import kotlinx.io.OutputStream
import kotlinx.io.PrintWriter
import kotlinx.serialization.json.JSON

class CommandSender(private val outputStream: OutputStream) {
    private val commandTransmitter = PrintWriter(outputStream, true)

    fun sendCommand(chatMessageCommand: ChatMessage) {
        Thread(Runnable {
            val chatMessageCommandAsJson =
                JSON.stringify(ChatMessage.serializer(), chatMessageCommand)
            commandTransmitter.println(chatMessageCommandAsJson)
            commandTransmitter.flush()
        }).start()
    }
}