/*
 - author: rafei
 - date: 08/10/2019
 - IPAddressDialogFragment class implement action dialog box for taking IP Address from user. ActionDialogBox is implemented with custom layout
 - and onclick Listeners for both buttons.
 */
package com.example.chatclientrib.chatClientUI

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.chatclientrib.R
import kotlinx.android.synthetic.main.dialog_ip_address.*
import java.lang.ClassCastException
import java.lang.IllegalStateException

class IPAddressDialogFragment : DialogFragment() {
    //Using this instance of the interface to deliver action events
    internal lateinit var listener: NoticeDialogListener

    //The activity that creates an instance of this dialog fragment must implement this interface in order to receive event callbacks.
    //Each method passes the DialogFragment in case the host needs to query it.

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            //Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            builder.setView(inflater.inflate(R.layout.dialog_ip_address, null))
                //builder.setMessage(R.string.dialog_set_ip_address)
                .setPositiveButton(R.string.set, DialogInterface.OnClickListener { dialog, id ->
                    //user set IP
                    listener.onDialogPositiveClick(this)
                })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                    //user cancelled the dialog
                    listener.onDialogNegativeClick(this)
                })
            //Create the AlertDialog object and return it
            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Verify that the host activity implements the callback interface
        try {
            //Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() + "must implement NoticeDialogListener"))
        }
    }
}