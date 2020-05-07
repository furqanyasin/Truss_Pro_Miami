package com.example.trusspromiami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.trusspromiami.adapters.SliderAdapter;
import com.example.trusspromiami.databinding.ActivityMainBinding;
import com.example.trusspromiami.models.SliderModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding activityMainBinding;
    boolean ishome = true;
    private Toolbar toolbar;

    private int selectedFragment = 1;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fragments.add(HomeFragment.newInstance());
        fragments.add(CategoryFragment.newInstance());
        fragments.add(CartFragment.newInstance());


        // navigation drawer navigation

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMainBinding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
       /// activityMainBinding.navigationView.getMenu().getItem(0).setChecked(true);
       // activityMainBinding.navigationView.getMenu().findItem(R.id.nav_home);
       // activityMainBinding.navigationView.setNavigationItemSelectedListener(this);

        activityMainBinding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {

                    case R.id.menu_home:
                        if (selectedFragment == 0)
                            return false;
                        fragment = HomeFragment.newInstance();
                        replaceFragment(fragment, 0);
                        return true;
                    case R.id.menu_categoty:
                        if (selectedFragment == 1)
                            return false;
                        fragment = CategoryFragment.newInstance();
                        replaceFragment(fragment, 1);
                        return true;

                    case R.id.menu_cart:
                        if (selectedFragment == 2)
                            return false;
                        fragment = CartFragment.newInstance();
                        replaceFragment(fragment, 2);
                        return true;

                    case R.id.menu_profile:
                        if (selectedFragment == 3)
                            return false;
                        fragment = CartFragment.newInstance();
                        replaceFragment(fragment, 3);
                        return true;
                }

                return false;
            }
        });


    }

    void replaceFragment(Fragment fragment, int index) {

        selectedFragment = index;
        activityMainBinding.tvNetwork.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment, "fragment_" + index).commit();
    }


    @Override
    public void onBackPressed() {
        ExitAlert();
    }

    private void ExitAlert() {
        if (activityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (ishome == false) {
                //activityMainBinding.navigationView.getMenu().getItem(0).setChecked(true);
                //activityMainBinding.navigationView.getMenu().findItem(R.id.nav_home);

            } else {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                moveTaskToBack(true);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setMessage("Do you want exit?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_aboutus) {

        }

        activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
