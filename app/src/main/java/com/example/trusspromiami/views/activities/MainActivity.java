package com.example.trusspromiami.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ActivityMainBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.views.fragments.AccountFragment;
import com.example.trusspromiami.views.fragments.CartFragment;
import com.example.trusspromiami.views.fragments.ContactUsFragment;
import com.example.trusspromiami.views.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private int selectedFragment = 1;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setListeners();
        initFragments();
        loadInitialFragment();
    }

    void initFragments() {

        fragments.add(HomeFragment.newInstance());
        fragments.add(CartFragment.newInstance());
        fragments.add(ContactUsFragment.newInstance());
        fragments.add(AccountFragment.newInstance());
    }

    void loadInitialFragment() {

        switch (AppConstants.LOAD_HOME_FRAGMENT_INDEX) {
            case 0:
            default:
                activityMainBinding.bottomBar.setSelectedItemId(R.id.menu_home);
                break;

            case 1:
                activityMainBinding.bottomBar.setSelectedItemId(R.id.menu_cart);
                break;

            case 2:
                activityMainBinding.bottomBar.setSelectedItemId(R.id.menu_contact);
                break;

            case 3:
                activityMainBinding.bottomBar.setSelectedItemId(R.id.menu_account);
                break;
        }
    }

    private void setListeners() {

        activityMainBinding.toolbar.ivSvFg.setOnClickListener(mOnClickListener);
        activityMainBinding.bottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    void replaceFragment(Fragment fragment, int index) {

        selectedFragment = index;
        getSupportFragmentManager().beginTransaction().replace(R.id.host_fragment, fragment, "fragment_" + index).commit();
    }

    private View.OnClickListener mOnClickListener = item -> {

        switch (item.getId()) {

            case R.id.iv_sv_fg:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        Fragment fragment;

        switch (item.getItemId()) {

            case R.id.menu_home:
            default:
                fragment = HomeFragment.newInstance();
                replaceFragment(fragment, 0);
                break;

            case R.id.menu_cart:
                fragment = CartFragment.newInstance();
                replaceFragment(fragment, 1);
                break;

            case R.id.menu_contact:
                fragment = ContactUsFragment.newInstance();
                replaceFragment(fragment, 2);
                break;

            case R.id.menu_account:
                fragment = AccountFragment.newInstance();
                replaceFragment(fragment, 3);
                break;

        }
        return true;
    };
}
