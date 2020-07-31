package com.example.trusspromiami.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.FragmentCartBinding;
import com.example.trusspromiami.views.activities.LanguageListing;
import com.example.trusspromiami.views.activities.OrderFormActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private FragmentCartBinding fragmentCardBinding;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        setListeners();
        return fragmentCardBinding.getRoot();
    }

    private void setListeners() {
        fragmentCardBinding.btnCartCheckout.setOnClickListener(mOnClickListener);
    }


    private View.OnClickListener mOnClickListener = item -> {

        switch (item.getId()) {
            case R.id.btn_cart_checkout:
                startActivity(new Intent(getContext(), OrderFormActivity.class));
                break;
        }
    };
}
