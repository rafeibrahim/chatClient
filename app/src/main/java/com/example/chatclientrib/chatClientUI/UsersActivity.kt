/*
 - author: rafei
 - date: 08/10/2019
 - UsersActivity is responsible for displaying ChatHistory using recyclerView. User can navigate to this activity through options menu of main activity
 */
package com.example.chatclientrib.chatClientUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatClientController
import com.example.chatclientrib.chatClientLogic.ChatClientDatabase
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        recyclerViewUsersActivity.layoutManager = LinearLayoutManager(this)
        recyclerViewUsersActivity.adapter =
            UserRecyclerViewAdapter(this, ChatClientController.getChatUserSet().toMutableList())
    }
}
