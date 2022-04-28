package com.hh.androidbaselibrary.ui.tab.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hh.androidbaselibrary.ui.tab.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By hHui on 2022/3/23 0023 下午 15:21
 */
public class TabAdapter extends FragmentStateAdapter {

    private List<Integer> colors;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        if (colors == null) {
            colors = new ArrayList<>();
        }
    }

    public void addColor(int color) {
        if (colors != null) {
            colors.add(color);
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ShowFragment.newInstance(colors, position);
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }
}