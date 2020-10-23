package com.bashapplication.main.fragment;

import android.os.Bundle;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;

public class PurchaseOrderFragment extends BaseFragment {


    public static PurchaseOrderFragment newInstance(String param1) {
        PurchaseOrderFragment fragment = new PurchaseOrderFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int layoutViewId() {
        return R.layout.purchase_order_layout;
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
