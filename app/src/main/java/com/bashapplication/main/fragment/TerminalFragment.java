package com.bashapplication.main.fragment;

import android.os.Bundle;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;

public class TerminalFragment extends BaseFragment {

    public static TerminalFragment newInstance(String param1) {
        TerminalFragment fragment = new TerminalFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.terminal_page_layout;
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
