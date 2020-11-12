package com.bashapplication.mine;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;

import butterknife.BindView;

/**
 * 我的关注
 */
public class MyFollowActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.follow_goods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
//        recyclerview.setLayoutManager(linearLayoutManager);
//        addressListAdapter = new AddressListAdapter(activity, addressBeanList);
//        recyclerview.setAdapter(addressListAdapter);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void setListener() {

    }


}