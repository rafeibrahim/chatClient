/*
 - author: rafei
 - date: 08/10/2019
 - ColorSelectionDialogFragment class implement action dialog box for taking color selection from user. It implements dialogbox with list of colors and
 - onClick listener for user color selection.
 */
package com.example.chatclientrib.chatClientUI


import android.os.Bundle
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import com.example.chatclientrib.R
import java.lang.ClassCastException
import java.lang.IllegalStateException


class ColorSelectionDialogFragment : DialogFragment() {
    //Use this instance of the interface to deliver action events
    internal lateinit var listener: NoticeColorDialogListener

    interface NoticeColorDialogListener{
        fun onClickListener(dialog: DialogFragment, postion: Int)
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            //Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.pick_color)
                .setItems(R.array.colors_array,
                DialogInterface.OnClickListener{dialog, which ->
                    // The which argument contains the index position of the selected item
                    listener.onClickListener(this, which)
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Verify that the host activity implements the callback interface
        try{
            //Instantiates the NoticeDialogListener so we can send events to the host
            listener = context as NoticeColorDialogListener
        }catch (e: ClassCastException){
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    "must implement NoticeColorDialogListener"))
        }
    }
}