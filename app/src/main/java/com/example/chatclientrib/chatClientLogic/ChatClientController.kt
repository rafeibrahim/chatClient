/*
 - author: rafei
 - date: 08/10/2019
 - ChatClientController object is the pivot class of this ChatClient project. It acts as a ControllerPart of ModelViewController (MVC) design pattern.
 - All userInterface(View) classes handle their functionality through ChatClientController. all customClasses (Model) update ChatClientController which in turn updates UI.
 */
package com.example.chatclientrib.chatClientLogic

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

object ChatClientController : ChatObserver {
    private lateinit var clientScoket: Socket
    private lateinit var inputStreamHandler: InputStreamHandler
    private lateinit var commandSender: CommandSender
    private lateinit var recyclerDisplay: RecyclerView
    private val mHandler = Handler(Looper.getMainLooper())

    //creates socket in a thread and start inputStreamHandler in other thread
    fun connect() {
        //implementing thread here so that we have client socket created before calling inputStreamHandler, becasue if we startListening Connector in separate thread we cannot
        //ensure that socket is being created before inputStreamHandler starts working.
        Thread(Runnable {
            clientScoket = SocketHandler.connect()
            this.inputStreamHandler = InputStreamHandler(this.clientScoket.getInputStream())
            this.inputStreamHandler.startListening()
            this.commandSender = CommandSender(this.clientScoket.getOutputStream())
        }).start()
    }

    //send input username string to Server to try to get it registered with ChatSever (Server response depends on availability of username)
    fun sendUsernameString(inputUsername: String) {
        val userNameSettingCommand = ChatMessage("username", "", "", inputUsername)
        this.commandSender.sendCommand(userNameSettingCommand)
        //if server sends acknowledgement ChatClientUser's username is intialized which means that Username is being set.
    }

    //method to check whether username is set or not
    fun usernameSet(): Boolean {
        return ChatClientUser.usernameInitialized()
    }

    fun sendMessage(inputMessage: String) {
        val simpleDateFormat = SimpleDateFormat("hh:mm")
        val currentTime = simpleDateFormat.format(Date())
        val newChatMessage =
            ChatMessage("message", inputMessage, currentTime, ChatClientUser.getUsername())
        this.commandSender.sendCommand(newChatMessage)
    }

    //sets username property of ChatClientUser after somekind of acknowledgement from server
    fun setUserName(username: String) {
        ChatClientUser.setUsername(username)
    }

    fun clientSocketinitialized(): Boolean {
        return ::clientScoket.isInitialized
    }

    fun setRecyclerDisplay(recyclerViewElement: RecyclerView) {
        this.recyclerDisplay = recyclerViewElement
    }

    override fun update() {
        mHandler.post(Runnable {
            Log.d("testing", "uiThread running")
            this.recyclerDisplay.adapter?.notifyDataSetChanged()
        })

    }

    fun sendHistoryCommand() {
        val historyCommand = ChatMessage("history", "", "", ChatClientUser.getUsername())
        this.commandSender.sendCommand(historyCommand)
    }

    fun sendUsersCommand() {
        val usersCommand = ChatMessage("users", "", "", ChatClientUser.getUsername())
        this.commandSender.sendCommand(usersCommand)
    }

    fun changeServerIPAddress(newIP: String) {
        //SocketHandler.disconnect()
        SocketHandler.changeServerIPAddress(newIP)
        this.connect()
    }

    fun chatUserExists(username: String): Boolean {
        return ChatClientDatabase.chatUserExists(username)
    }

    fun getChatUserMessageCount(username: String): Int {
        return ChatClientDatabase.chatUserMessageCount(username)
    }

    fun getIncomingMessagesList(): MutableList<ChatMessage>{
        return ChatClientDatabase.getIncomingMessagesList()
    }

    fun getChatHistoryList(): MutableList<ChatMessage>{
        return ChatClientDatabase.getChatHistory()
    }

    fun getChatUserSet(): MutableSet<ChatUser>{
        return ChatClientDatabase.getChatUsers()
    }
}