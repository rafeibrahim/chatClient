/*
 - author: rafei
 - date: 08/10/2019
 - Settings activity is used for displaying UI to change sever's IP address and change message text color. It opens alertDialog Fragment both for changing IP address
 - and message text color. Receive call backs from dialogs and use ChalClientController object to implement user input.
 */
package com.example.chatclientrib.chatClientUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.chatclientrib.R
import com.example.chatclientrib.chatClientLogic.ChatClientController
import kotlinx.android.synthetic.main.dialog_ip_address.*
import java.lang.IllegalStateException

class SettingsActivity : AppCompatActivity(), IPAddressDialogFragment.NoticeDialogListener,
    ColorSelectionDialogFragment.NoticeColorDialogListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var IPdialog: IPAddressDialogFragment
    private lateinit var colorDialog: ColorSelectionDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        sharedPreferences = SharedPreferences(this)
    }

    fun onClickChangeIPButton(view: View) {
        showIPInputDialog()
    }

    fun onClickChangeTextColor(view: View) {
        showColorSelectionDialog()
    }

    fun showColorSelectionDialog() {
        //create an instance of ColorSelectionDialog and show it.
        colorDialog = ColorSelectionDialogFragment()
        colorDialog.show(supportFragmentManager, "ColorSelectionFragment")
    }

    fun showIPInputDialog() {
        //Create an instance of the IPAddressDialog fragment and show it
        IPdialog = IPAddressDialogFragment()
        IPdialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    //click listener for color List Alert Dialog
    override fun onClickListener(dialog: DialogFragment, position: Int) {
        Log.d("colorTesting", "onlistitemclicked  " + position)
        when (position) {
            0 -> {
                Log.d("colorTesting", "blue line executed " + position)
                sharedPreferences.save("textColor", "blue")
            }
            1 -> {
                Log.d("colorTesting", "black line executed " + position)
                sharedPreferences.save("textColor", "black")
                //findViewById<TextView>(R.id.message_text_view).(ContextCompat.getColor(MainActivity.this, R.color.colorOwnMessages))

            }
            2 -> {
                Log.d("colorTesting", "blue line executed " + position)
                sharedPreferences.save("textColor", "pink")
            }

        }
    }

    //The IPdialog fragment receives a reference to this activity through the Fragment.onAttach() callback, which it uses to call
    //the following methods defined by the NoticeDialogFragment.NoticeDialogListener interface


    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d("dialogTesting", "onDialogPostiveButtonClicked")
        try {
            Log.d("dialogTesting", dialog.dialog?.newIPAddress?.text.toString())
            ChatClientController.changeServerIPAddress(dialog.dialog?.newIPAddress?.text.toString())
        } catch (e: IllegalStateException) {
            Log.d("dialogTesting", "null String Detected   " + e.message)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        //Do nothing
    }
}
