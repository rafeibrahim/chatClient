/*
 - author: rafei
 - date: 08/10/2019
 - SocketHandler object class takes care of creating new socket to connect to ChatServer with given IP address and port number
 */
package com.example.chatclientrib.chatClientLogic
import android.util.Log
import java.lang.Exception
import java.net.InetAddress
import java.net.Socket
object SocketHandler {
    private lateinit var socket: Socket
    //private var ipAddress: String = "10.0.2.2"
    private var ipAddress: String = "192.168.1.200"

    fun connect(): Socket {
           try {
                socket = Socket(InetAddress.getByName(this.ipAddress), 30005)
           }catch (e: Exception){
                Log.d("TestingChatClient", "Exception caught at socket creation: ${e.message}")
            }
        return socket
   }

    fun disconnect(){
        socket.close()
    }


    fun changeServerIPAddress(ipAddress: String){
        this.ipAddress = ipAddress
    }
 }