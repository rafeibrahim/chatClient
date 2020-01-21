/*
 - author: rafei
 - date: 08/10/2019
 - UserRecyclerViewAdapter provides adapter to recyclerView displaying users in UsersActivity
 */
package com.example.chatclientrib.chatClientUI

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatUser

class UserRecyclerViewAdapter(
    private val context: Context,
    private val dataList: MutableList<ChatUser>
) : RecyclerView.Adapter<UserRecyclerViewAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessageViewHolder {
        val messageView =
            LayoutInflater.from(context).inflate(R.layout.list_message_view, viewGroup, false)
        return MessageViewHolder(messageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val stringChatUser = dataList[position].toString()
        val chatUserParts = stringChatUser.split("\n")
        val formattedString = "<big><b>" + chatUserParts[0] + "</b></big><br>" + chatUserParts[1]

        holder.itemView.findViewById<TextView>(R.id.message_text_view).text = (Html.fromHtml(formattedString, Html.FROM_HTML_MODE_LEGACY))

    }

    class MessageViewHolder(messageView: View) : RecyclerView.ViewHolder(messageView) {

    }
}