package com.hh.androidbaselibrary.ui.tab.fragment;

import android.view.LayoutInflater;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.hh.androidbaselibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By hHui on 2022/3/23 0023 下午 15:28
 */
public class ShowFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    public static ShowFragment newInstance(List<Integer> colors, int item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("color", (ArrayList<Integer>) colors);
        bundle.putInt("item", item);
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.<FrameLayout>findViewById(R.id.fl_show)
                .setBackgroundResource(((ArrayList<Integer>) getArguments()
                        .getSerializable("color")).get(getArguments().getInt("item")));
        view.<TextView>findViewById(R.id.tv_show).setText("第 " + (getArguments().getInt("item") + 1) + " 个页面");
        super.onViewCreated(view, savedInstanceState);
    }
}
