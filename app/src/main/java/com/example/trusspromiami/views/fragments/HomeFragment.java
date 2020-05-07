package com.example.trusspromiami.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.trusspromiami.R;
import com.example.trusspromiami.adapters.SliderAdapter;
import com.example.trusspromiami.models.SliderModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ViewPager bannerSliderViewPager;
    private TabLayout indicator;
    private Timer timer;
    final private long DELAY_TIME = 2000;
    final private long PERIOD_TIME = 2000;
    private int currentPage = 2;
    List<SliderModel> sliderModelList;
    boolean ishome = true;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // slider images
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_Pager);
        indicator = view.findViewById(R.id.indicator);
        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.one));
        sliderModelList.add(new SliderModel(R.drawable.two));
        sliderModelList.add(new SliderModel(R.drawable.three));
        sliderModelList.add(new SliderModel(R.drawable.four));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        indicator.setupWithViewPager(bannerSliderViewPager, true);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLope();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLope();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopBannerSlideShow();
                }
                return false;
            }
        });
        // slider images
        return view;
    }


    ///slider banner functions
    private void pageLope() {
        if (currentPage == sliderModelList.size()) {
            bannerSliderViewPager.setCurrentItem(currentPage++, false);
        }

    }

    private void startBannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()) {
                    currentPage = 0;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);


    }

    private void stopBannerSlideShow() {
        timer.cancel();
    }
    ///slider banner functions


}
