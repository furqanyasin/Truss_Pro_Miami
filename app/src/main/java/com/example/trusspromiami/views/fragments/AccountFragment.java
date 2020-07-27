package com.example.trusspromiami.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.FragmentAccountBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.views.activities.AccountInformationActivity;
import com.example.trusspromiami.views.activities.LanguageListing;
import com.example.trusspromiami.views.activities.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private FragmentAccountBinding fragmentAccountBinding;
    private String token;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        token = ((BaseActivity) getActivity()).appPreference.getString(AppConstants.TOKEN);

        if (token.isEmpty())
            fragmentAccountBinding.tvLoginLogout.setText(R.string.login);
        else
            fragmentAccountBinding.tvLoginLogout.setText(R.string.logout);


        setListeners();
        return fragmentAccountBinding.getRoot();
    }

    private void setListeners() {
        fragmentAccountBinding.profile.setOnClickListener(mOnClickListener);
        fragmentAccountBinding.orders.setOnClickListener(mOnClickListener);
        fragmentAccountBinding.contactUs.setOnClickListener(mOnClickListener);
        fragmentAccountBinding.language.setOnClickListener(mOnClickListener);
        fragmentAccountBinding.ivContainer.setOnClickListener(mOnClickListener);
        fragmentAccountBinding.tvLoginLogout.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = item -> {

        switch (item.getId()) {

            case R.id.language:
                startActivity(new Intent(getContext(), LanguageListing.class));
                break;

            case R.id.tv_login_logout:
                if (token.isEmpty())
                    startActivity(new Intent(getContext(), LoginActivity.class));
                else {
                    ((BaseActivity) getActivity()).appPreference.setString(AppConstants.TOKEN, AppConstants.EMPTY_STRING);
                    token = ((BaseActivity) getActivity()).appPreference.getString(AppConstants.TOKEN);
                    fragmentAccountBinding.tvLoginLogout.setText(R.string.login);
                }

                break;

            case R.id.iv_container:
            case R.id.profile:
                startActivity(new Intent(getContext(), AccountInformationActivity.class));
                break;
        }
    };
}
