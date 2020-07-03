package com.example.trusspromiami.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.tv.TvContract;

import com.example.trusspromiami.R;

public class AppHelper {

    public static ProgressDialog progressDialog;

    public static void showProgressDialogue(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.processing));
        progressDialog.setCancelable(false);
    }
}
