/*
 - author: rafei
 - date: 08/10/2019
 - SharedPreferences class provides method for writing to sharedPreferences and getting value from sharedPreferences. SharedPreferences is being used
 - in this project to save customer preference regarding chatMessageTextColor.
 */
package com.example.chatclientrib.chatClientUI

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(val context: Context) {
    private val PREF_NAME = "com.example.chatclientrib.settings.file"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, value)
        editor.commit()
    }

    fun getValueString(KEY_NAME: String): String?{
        return sharedPref.getString(KEY_NAME, null)
    }
}