package com.bashapplication.main.fragment;

import android.os.Bundle;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;

public class FunctionFragment extends BaseFragment {


    public static FunctionFragment newInstance(String param1) {
        FunctionFragment fragment = new FunctionFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int layoutViewId() {
        return R.layout.function_page_layout;
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
