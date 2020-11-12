package com.bashapplication.mine;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.mine.adapter.AddressListAdapter;
import com.bashapplication.mine.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 地址列表
 */
public class AddressListActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private AddressListAdapter addressListAdapter;
    private List<AddressBean> addressBeanList = new ArrayList<>();

    @Override
    protected int layoutViewId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.address_list);

        AddressBean addressBean = new AddressBean("李晓明","123****4566","河南省洛阳市涧西区方林路东方红广场B座","moren");

        AddressBean addressBean2 = new AddressBean("李晓明","123****4566","河南省洛阳市涧西区方林路东方红广场B座","gongsi");

        addressBeanList.add(addressBean);
        addressBeanList.add(addressBean2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerview.setLayoutManager(linearLayoutManager);
        addressListAdapter = new AddressListAdapter(activity, addressBeanList);
        recyclerview.setAdapter(addressListAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }


}