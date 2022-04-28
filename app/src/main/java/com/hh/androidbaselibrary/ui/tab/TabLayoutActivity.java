package com.hh.androidbaselibrary.ui.tab;

import android.graphics.Color;
import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.hh.androidbaselibrary.R;
import com.hh.androidbaselibrary.ui.tab.adapter.TabAdapter;
import com.hh.baselibrary.mvp.BaseActivity;

public class TabLayoutActivity extends BaseActivity {

    private ViewPager2 vpTab;
    private TabLayout tabVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        TabAdapter adapter = new TabAdapter(this);
        vpTab = findViewById(R.id.vp_tb);
        tabVp = findViewById(R.id.tb_vp);
        vpTab.setAdapter(adapter);
        tabVp.setTabTextColors(Color.parseColor("#111111"),Color.parseColor("#0371DD"));

        adapter.addColor(android.R.color.darker_gray);
        adapter.addColor(android.R.color.holo_red_dark);
//        adapter.addColor(android.R.color.holo_green_dark);
//        adapter.addColor(android.R.color.holo_blue_dark);
//        adapter.addColor(android.R.color.holo_purple);
//        adapter.addColor(android.R.color.holo_orange_dark);

        tabVp.addTab(tabVp.newTab().setText("第一"));
        tabVp.addTab(tabVp.newTab().setText("第二"));
//        tabVp.addTab(tabVp.newTab().setText("第三个界面"));
//        tabVp.addTab(tabVp.newTab().setText("第四个界面"));
//        tabVp.addTab(tabVp.newTab().setText("第五个界面"));
//        tabVp.addTab(tabVp.newTab().setText("第六个界面"));

        tabVp.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpTab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabVp.setScrollPosition(position, 0, false);
            }
        });
    }

    @Override
    public void initView() {

    }
}