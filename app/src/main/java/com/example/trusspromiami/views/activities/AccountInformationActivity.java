package com.example.trusspromiami.views.activities;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityAccountInformationBinding;

public class AccountInformationActivity extends BaseActivity {

    private ActivityAccountInformationBinding activityAccountInformationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAccountInformationBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_information);
        setViews();
        setListeners();
    }

    private void setViews() {
        activityAccountInformationBinding.toolbar.activityTitle.setText(R.string.account_information);
        activityAccountInformationBinding.toolbar.actionBtn.setVisibility(View.GONE);
    }

    private void setListeners() {
        activityAccountInformationBinding.toolbar.ivBackArrow.setOnClickListener(onClickListeners);
    }

    private View.OnClickListener onClickListeners = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}