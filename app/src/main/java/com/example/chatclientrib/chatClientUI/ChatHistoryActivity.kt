/*
 - author: rafei
 - date: 08/10/2019
 - ChatHistoryActivity is responsible for displaying ChatHistory using recyclerView. It is using the same recyclerView adapter used for displaying messages in MainActivity
 - User can navigate to this activity through optionsMenu of Main Activity
 */
package com.example.chatclientrib.chatClientUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatClientController
import com.example.chatclientrib.chatClientLogic.ChatMessage
import kotlinx.android.synthetic.main.activity_chat_history.*

class ChatHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_history)

        recyclerViewChatHistory.layoutManager = LinearLayoutManager(this)
        recyclerViewChatHistory.adapter = MessageRecyclerViewAdapter(
            this,
            ChatClientController.getChatHistoryList(),
            { chatMessage: ChatMessage -> chatMessageClicked(chatMessage) })
    }

    //Listener is only toasting clicked chatMessage
    fun chatMessageClicked(chatMessage: ChatMessage) {
        Log.d("recyclerItemListener", "chatMessageClicked")
        Toast.makeText(this, "$chatMessage", Toast.LENGTH_SHORT).show()
    }
}
