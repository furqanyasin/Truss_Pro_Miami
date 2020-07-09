package com.example.trusspromiami.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.FragmentHomeBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.listeners.OnItemClickInterface;
import com.example.trusspromiami.models.category.Banner;
import com.example.trusspromiami.models.category.CategoriesData;
import com.example.trusspromiami.retrofit.retrofitClients.CategoriesApiClient;
import com.example.trusspromiami.views.activities.ProductListing;
import com.example.trusspromiami.views.adapters.BannerImageAdapter;
import com.example.trusspromiami.views.adapters.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private ArrayList<CategoriesData> categories = new ArrayList<>();
    private ArrayList<Banner> banners = new ArrayList<>();
    private CategoryAdapter adapter;
    private List<ImageView> dots = new ArrayList<>();
    int currentPage = 0;

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

        getCategoryList();
        getBannersList();
        setSlider();
        setupAutoPager();
        setAdapter();
        return fragmentHomeBinding.getRoot();
    }

    private void setAdapter() {

        if (getContext() != null) {
            adapter = new CategoryAdapter(getContext(), onItemClickInterface);
            fragmentHomeBinding.rvCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
            fragmentHomeBinding.rvCategory.setAdapter(adapter);
            fragmentHomeBinding.rvCategory.setHasFixedSize(true);
        }
    }


    private void getCategoryList() {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).progressDialog.show();
        CategoriesApiClient.getCategoriesCall(0, listener);
    }

    private void getBannersList() {
        banners = Banner.getBannersList();
    }

    private void setSlider() {

        if (fragmentHomeBinding.dots.getChildCount() > 0)
            fragmentHomeBinding.dots.removeAllViews();

        BannerImageAdapter bannerImageAdapter = new BannerImageAdapter(getContext(), banners);
        fragmentHomeBinding.vpImages.setAdapter(bannerImageAdapter);

        Display display = ((android.view.WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        fragmentHomeBinding.vpImages.getLayoutParams().height = ((int) (display.getWidth() * 0.6));
        fragmentHomeBinding.vpImages.getLayoutParams().width = ((int) (display.getWidth() * 1.0));

        currentPage = 0;
        addDots();

    }

    private void addDots() {

        dots.clear();

        if (banners.size() < 2) {
            fragmentHomeBinding.dots.setVisibility(View.GONE);
            return;
        }

        for (int i = 0; i <= banners.size() - 1; i++) {
            ImageView dot = new ImageView(getContext());
            dot.setImageDrawable(getContext().getResources().getDrawable(R.drawable.empty_circle));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );


            fragmentHomeBinding.dots.addView(dot, params);

            dot.setTag(R.string.banner_circle_tag, i);

            dot.setOnClickListener(v -> {

                onBannerCircleClicked(Integer.parseInt(v.getTag(R.string.banner_circle_tag).toString()));
            });

            dots.add(dot);
        }

        selectDot(0);

        fragmentHomeBinding.vpImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                currentPage = position;
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void onBannerCircleClicked(int parseInt) {
        currentPage = parseInt;
        fragmentHomeBinding.vpImages.setCurrentItem(parseInt);
    }

    private void selectDot(int idx) {

        if (getContext() == null)
            return;

        Resources res = getResources();
        for (int i = 0; i <= banners.size() - 1; i++) {
            int drawableId = (i == idx) ? (R.drawable.filled_circle) : (R.drawable.empty_circle);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

    private void setupAutoPager() {
        final Handler handler = new Handler();

        final Runnable update = () -> {

            fragmentHomeBinding.vpImages.setCurrentItem(currentPage, true);
            if (currentPage == banners.size()) {
                currentPage = 0;
            } else {

                ++currentPage;
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 1000, 3500);
    }

    private IResponse<ArrayList<CategoriesData>, String> listener = new IResponse<ArrayList<CategoriesData>, String>() {
        @Override
        public void onSuccess(ArrayList<CategoriesData> result) {

            ((BaseActivity) getActivity()).progressDialog.hide();
            if (result != null && !result.isEmpty()) {
                categories = result;
                adapter.setData(categories);
            }
        }

        @Override
        public void onFailure(String error) {
            ((BaseActivity) getActivity()).progressDialog.hide();
            ((BaseActivity) getActivity()).showToast(error);
        }
    };

    private OnItemClickInterface onItemClickInterface = index -> {
        Intent intent = new Intent(getContext(), ProductListing.class);
        intent.putExtra(AppConstants.CATEGORY_OBJ, categories.get(index));
        startActivity(intent);
    };
}
