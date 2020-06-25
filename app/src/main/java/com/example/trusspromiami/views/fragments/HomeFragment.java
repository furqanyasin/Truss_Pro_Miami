package com.example.trusspromiami.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.trusspromiami.R;
import com.example.trusspromiami.adapters.SliderAdapter;
import com.example.trusspromiami.databinding.FragmentHomeBinding;
import com.example.trusspromiami.models.SliderModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private ViewPager bannerSliderViewPager;
    private TabLayout indicator;
    private Timer timer;
    final private long DELAY_TIME = 2000;
    final private long PERIOD_TIME = 2000;
    private int currentPage = 2;
    List<SliderModel> sliderModelList;

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

        fragmentHomeBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home,
                container,
                false);

        setSlider();
        return fragmentHomeBinding.getRoot();
    }

    private void setSlider() {
        // slider images
        bannerSliderViewPager = fragmentHomeBinding.sliderBanner.bannerSliderViewPager;
        indicator = fragmentHomeBinding.sliderBanner.indicator;

        sliderModelList = SliderModel.getSliderList();

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        indicator.setupWithViewPager(bannerSliderViewPager, true);
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener((v, event) -> {
            pageLope();
            stopBannerSlideShow();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                stopBannerSlideShow();
            }
            return false;
        });
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

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
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
}
