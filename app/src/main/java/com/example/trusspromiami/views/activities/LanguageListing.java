package com.example.trusspromiami.views.activities;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.trusspromiami.App;
import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityLanguageListingBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.helpers.DialogueHelper;
import com.example.trusspromiami.helpers.LanguageHelper;
import com.example.trusspromiami.listeners.DialogueListener;

public class LanguageListing extends BaseActivity {

    private ActivityLanguageListingBinding activityLanguageListingBinding;
    private String languageCode = "en";
    private DialogueHelper dialogueHelper;

    private DialogueListener dialogueListener = new DialogueListener() {
        @Override
        public void positiveButtonClick(Boolean click) {
            LanguageHelper.setLanguage(App.getContext(), languageCode);
            relaunchApp(LanguageListing.this);
        }

        @Override
        public void negativeButtonClick(Boolean click) {

        }
    };

    private View.OnClickListener mOnClickListener = item -> {

        switch (item.getId()) {

            case R.id.english_lang: {
                languageCode = AppConstants.ENGLISH;
                break;
            }
            case R.id.spanish_lang: {
                languageCode = AppConstants.SPANISH;
                break;
            }
            case R.id.portuguese_lang: {
                languageCode = AppConstants.PORTUGUES;
                break;
            }

            case R.id.action_btn:
                DialogueHelper.showDialogue(this,
                        getString(R.string.note),
                        getString(R.string.app_lang_message),
                        getString(android.R.string.yes),
                        getString(android.R.string.no),
                        dialogueListener);
                break;
        }
        setView();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLanguageListingBinding = DataBindingUtil.setContentView(this, R.layout.activity_language_listing);
        languageCode = LanguageHelper.getLanguage(this);
        setView();
        setListeners();
    }

    private void setView() {

        activityLanguageListingBinding.mtoolbar.activityTitle.setText(R.string.select_language);
        activityLanguageListingBinding.mtoolbar.actionBtn.setText(R.string.done);
        activityLanguageListingBinding.ivEngCheck.setVisibility(View.GONE);
        activityLanguageListingBinding.ivSpanCheck.setVisibility(View.GONE);
        activityLanguageListingBinding.ivPortCheck.setVisibility(View.GONE);

        switch (languageCode) {

            case AppConstants.SPANISH:
                activityLanguageListingBinding.ivSpanCheck.setVisibility(View.VISIBLE);
                break;

            case AppConstants.PORTUGUES:
                activityLanguageListingBinding.ivPortCheck.setVisibility(View.VISIBLE);
                break;

            case AppConstants.ENGLISH:
            default:
                activityLanguageListingBinding.ivEngCheck.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setListeners() {
        activityLanguageListingBinding.mtoolbar.actionBtn.setOnClickListener(mOnClickListener);
        activityLanguageListingBinding.englishLang.setOnClickListener(mOnClickListener);
        activityLanguageListingBinding.spanishLang.setOnClickListener(mOnClickListener);
        activityLanguageListingBinding.portugueseLang.setOnClickListener(mOnClickListener);
    }
}
