package com.example.trusspromiami.views.activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;

public class AccountInformationActivity extends BaseActivity {

    private ViewDataBinding accountInformationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountInformationActivity = DataBindingUtil.setContentView(this, R.layout.activity_account_information);

        setViews();
    }

    private void setViews() {

    }

}
