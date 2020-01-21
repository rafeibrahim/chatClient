/*
 - author: rafei
 - date: 08/10/2019
 - MessageRecyclerViewAdapter implements adapter for displaying chatMessages. It also implements onClick Listener for elements of recyclerView.
 */
package com.example.chatclientrib.chatClientUI

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatClientUser
import com.example.chatclientrib.chatClientLogic.ChatMessage
import kotlinx.android.synthetic.main.list_message_view.view.*


class MessageRecyclerViewAdapter(private val context: Context, private val dataList:MutableList<ChatMessage>, val clickListener: (ChatMessage) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //layoutInflater: takes ID from layout defined in XML
        //Instantiates the layout XML into corresponding view objects
        //Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_message_view, viewGroup, false)
        sharedPreferences = SharedPreferences(context)
        return MessageViewHolderNew(view, context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //populate ViewHolder with data that corresponds to position in te list which we are told to load
        (holder as MessageViewHolderNew).bind(dataList[position], clickListener)

    }


    class MessageViewHolderNew(val messageItemView: View, val context: Context): RecyclerView.ViewHolder(messageItemView) {
        private lateinit var sharedPreferences: SharedPreferences


        fun bind(chatMessage : ChatMessage, clickListener: (ChatMessage) -> Unit){
            sharedPreferences = SharedPreferences(context)

            if(chatMessage.getUsername().equals(ChatClientUser.getUsername())){
                messageItemView.message_text_view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorOwnMessages))
            }

            if(sharedPreferences.getValueString("textColor") != null){
                val color = sharedPreferences.getValueString("textColor")
                if (color.equals("black")){
                    messageItemView.message_text_view.setTextColor(ContextCompat.getColor(context, R.color.black))

                }else if(color.equals("blue")){
                    messageItemView.message_text_view.setTextColor(ContextCompat.getColor(context, R.color.blue))
                }else if(color.equals("pink")){
                    messageItemView.message_text_view.setTextColor(ContextCompat.getColor(context, R.color.pink))
                }

            }

            val chatMessageString= chatMessage.toString()
            val chatMessageParts = chatMessageString.split("\n")
            val formattedString = "<small>"+ chatMessageParts[0] +"</small><br><b><big'>" + chatMessageParts[1] + "</big></b><br><small>" + chatMessageParts[2] + "</small>"
            val startingIndex = chatMessageString.indexOf('\n') + 1
            val endingIndex = startingIndex + chatMessageParts.size


            //this.messageItemView.message_text_view.text = chatMessage.toString()
            this.messageItemView.message_text_view.text = (Html.fromHtml(formattedString, Html.FROM_HTML_MODE_LEGACY))


            this.messageItemView.setOnClickListener({clickListener(chatMessage)})
        }

    }

}
