package com.example.weatherman.ui.tutorial;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.weatherman.R;
import com.example.weatherman.databinding.ActivityViewsSliderBinding;
import com.example.weatherman.ui.base.ViewModelFactory;
import com.example.weatherman.ui.mainmenu.MainActivity;
import com.example.weatherman.ui.tutorial.adapter.ViewPagerFragmentAdapter;

public class TutorialActivity extends AppCompatActivity {
    private static final int SLIDES = 3;
    private TextView[] dots;
    private ActivityViewsSliderBinding binding;
    private TutorialViewModel tutorialViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = new ViewModelFactory(getApplication());
        tutorialViewModel = new ViewModelProvider(this, factory).get(TutorialViewModel.class);
        binding = ActivityViewsSliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);
        binding.btnSkip.setOnClickListener(v -> launchHomeScreen());
        binding.btnNext.setOnClickListener(v -> {
            int current = binding.viewPager.getCurrentItem() + 1;
            if (current < SLIDES) {
                binding.viewPager.setCurrentItem(current, true);
            } else {
                tutorialViewModel.setFirstRun();
                launchHomeScreen();
            }
        });
        addBottomDots(0);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[SLIDES];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    /*
     * ViewPager page change listener
     */
    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == SLIDES - 1) {
                // last page. make button text to GOT IT
                binding.btnNext.setText(getString(R.string.start));
                binding.btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                binding.btnNext.setText(getString(R.string.next));
                binding.btnSkip.setVisibility(View.VISIBLE);
            }
        }
    };


}
