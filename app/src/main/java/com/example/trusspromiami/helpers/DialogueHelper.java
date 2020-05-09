package com.example.trusspromiami.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.trusspromiami.R;
import com.example.trusspromiami.listeners.DialogueListener;
import com.google.android.material.dialog.InsetDialogOnTouchListener;

public class DialogueHelper {

    DialogueHelper() {

    }

    public static void showDialogue(Context context, String title, String message, String positiveBtn, String negativeBtn, DialogueListener dialogueListener) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogueListener.positiveButtonClick(true);
                    }
                })

                .setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                    }
                })
                .show();
    }
}
