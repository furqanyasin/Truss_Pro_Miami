package com.example.trusspromiami.baseClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trusspromiami.R;
import com.example.trusspromiami.helpers.LanguageHelper;
import com.example.trusspromiami.views.activities.MainActivity;

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog progressDialog;
    private View mToolbar;

    // override the base context of application to update default locale for this activity
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.processing));
        progressDialog.setCancelable(false);
    }

    public void setLayout(int layoutId, Integer toolbar, boolean isHomeEnable) {
        setContentView(layoutId);
        if (toolbar != null) {
            mToolbar = findViewById(toolbar);
            View backBtn = findViewById(R.id.iv_back_arrow);
            View actionBtn = findViewById(R.id.action_btn);

            backBtn.setOnClickListener(onClickListeners);
            actionBtn.setOnClickListener(onClickListeners);

            if (isHomeEnable)
                actionBtn.setVisibility(View.VISIBLE);
        }
    }

    public void setActivityTitle(String title) {
        TextView view = (TextView) mToolbar.findViewById(R.id.activity_title);
        view.setText(title);
    }

    public void relaunchApp(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        Runtime.getRuntime().exit(0);
        activity.finish();
    }

    private View.OnClickListener onClickListeners = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.iv_back_arrow)
                finish();
        }
    };

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}