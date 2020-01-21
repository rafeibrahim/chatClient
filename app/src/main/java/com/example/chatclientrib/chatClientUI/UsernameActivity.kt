/*
 - author: rafei
 - date: 08/10/2019
 - UsernameActivity provides userInterface for registering username with the server. This activity is automatically started from MainActivity if
 - username is not registered with server.
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
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatClientController
import kotlinx.android.synthetic.main.activity_user_name.*


class UsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_name)
    }

    fun onClickRegisterButton(view: View){
        if(!inputUsername.text.toString().equals("")) {
            if (ChatClientController.clientSocketinitialized()) {
                Log.d("testing", "UserNameActivity " + inputUsername.text.toString())
                ChatClientController.sendUsernameString(inputUsername.text.toString())

                //if(ChatClientController.usernameSet()) {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
                //}
            } else {
                Toast.makeText(
                    applicationContext,
                    "connection to server not made yet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else{
            Toast.makeText(
                applicationContext,
                "username cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.username_activity_options_menu, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.about){
            val intent = Intent(this, AboutActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        }else if(item.itemId == R.id.settings){
            val intent = Intent(this, SettingsActivity::class.java).apply {

            }
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
