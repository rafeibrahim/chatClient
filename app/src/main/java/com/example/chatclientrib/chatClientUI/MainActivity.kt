/*
 - author: rafei
 - date: 08/10/2019
 - MainActivity is the activity which is started when Application is started. It redirects user to Username Activity if username is not yet
 - registered with chatServer. It is the activity which displays UserInterface for user to send messages and view incoming messages. User can go to
 - various activities by selecting different options from OptioinsMenu of this activity.
 */
package com.example.chatclientrib.chatClientUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //registers ChatClientController (object class) as ChatObserver to ChatClientDatabase
        ChatClientDatabase.registerChatObserver(ChatClientController)
        //sets reference to recyclerView in ChatClientController
        ChatClientController.setRecyclerDisplay(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MessageRecyclerViewAdapter(
            this,
            ChatClientController.getIncomingMessagesList(),
            { chatMessage: ChatMessage -> chatMessageClicked(chatMessage) })


        if (!ChatClientController.clientSocketinitialized()) {
            ChatClientController.connect()
        }

        //starts Username activity if username is not yet registered with ChatServer
        if (!ChatClientController.usernameSet()) {
            Log.d("testing", "MainActivity username not initialized")
            val intent = Intent(this, UsernameActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }


    //implements onClickListener for elements of RecyclerView. It toasts username, status of username, and messages sent on screen.
    fun chatMessageClicked(chatMessage: ChatMessage) {
        Log.d("recyclerItemListener", "chatMessageClicked")
        if (ChatClientController.chatUserExists(chatMessage.getUsername())) {
            Toast.makeText(
                this,
                "Username: ${chatMessage.getUsername()} \nStatus: Active \nMessages Sent: ${ChatClientController.getChatUserMessageCount(
                    chatMessage.getUsername()
                )}",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                this,
                "Username: ${chatMessage.getUsername()} \nStatus: Passive",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    //implementation for Send button which sends messages to server
    fun onClickSendButton(view: View) {
        Log.d("Testing", "onClickSendButtonPressed")
        ChatClientController.sendMessage(messageEditText.text.toString())
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.history) {
            ChatClientController.sendHistoryCommand()
            val intent = Intent(this, ChatHistoryActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        } else if (item.itemId == R.id.users) {
            ChatClientController.sendUsersCommand()
            val intent = Intent(this, UsersActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        } else if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        }else if(item.itemId == R.id.about){
            val intent = Intent(this, AboutActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
