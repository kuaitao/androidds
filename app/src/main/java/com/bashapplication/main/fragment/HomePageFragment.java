package com.bashapplication.main.fragment;

import android.os.Bundle;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;

public class HomePageFragment extends BaseFragment {


    public static HomePageFragment newInstance(String param1) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int layoutViewId() {
        return R.layout.home_page_layout;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
