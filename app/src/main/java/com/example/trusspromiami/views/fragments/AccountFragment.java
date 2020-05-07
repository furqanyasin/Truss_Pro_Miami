package com.example.trusspromiami.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trusspromiami.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}
