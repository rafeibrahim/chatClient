/*
 - author: rafei
 - date: 08/10/2019
 - About Activity displays application overview and basic guidelines for user
 */
package com.example.chatclientrib.chatClientUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclientrib.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    val aboutText = "\nOverview:\n\nChatMessenger is a chatting app which can send and receive messages. Sent messages are broadcasted by server to all active Users\n" +
            "\n Basic User Guidelines\n " +
            "\n1. Registering Username: Register with ChatServer using a username to start chatting.\n" +
            "\n2. Chatting: Start sending messages after registeration. You will also start seeing messages from other users popping on your screen\n" +
            "\n3. History & ChatUsers: Click menu buttons to see chat history and other active users\n" +
            "\n4. Settings: In Settings you can change ChatServer IP Address and color of text messages.\n" +
            "\nThank you for using ChatMessenger."



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        textViewAbout.setText(aboutText)
    }
}
