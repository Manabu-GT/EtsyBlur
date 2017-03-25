package com.ms.square.android.etsyblurdemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ms.square.android.etsyblurdemo.R;
import com.ms.square.android.etsyblurdemo.ui.fragment.PlaceholderFragment;
import com.ms.square.android.etsyblurdemo.ui.view.ViewPagerIndicator;
import com.ms_square.etsyblur.BlurringView;

public class ViewPagerActivity extends AppCompatActivity {

    private final static int PAGE_COUNT = 3;

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerIndicator indicator;
    private BlurringView blurringView;

    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        indicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        indicator.setPageCount(PAGE_COUNT);

        blurringView = (BlurringView) findViewById(R.id.blurring_view);
        blurringView.blurredView(pager);

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentPosition(position);
            }
        });
    }

    private static class SectionPagerAdapter extends FragmentStatePagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position, R.layout.fragment_place_holder_number);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
