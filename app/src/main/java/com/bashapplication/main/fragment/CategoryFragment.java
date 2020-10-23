package com.bashapplication.main.fragment;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.category.adapter.CategoryLeftAdapter;
import com.bashapplication.category.bean.CategoryLeftBean;
import com.bashapplication.view.GeneralPtrView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    @BindView(R.id.ptrview)
    GeneralPtrView ptrview;
    @BindView(R.id.vg_content)
    ViewGroup vgContent;

    private CategoryLeftAdapter leftAdapter;
    private List<CategoryLeftBean> list_left = new ArrayList<CategoryLeftBean>();

    private List<DelegateAdapter.Adapter> adapterList = new ArrayList<>();
    private DelegateAdapter delegateAdapter;

    public static CategoryFragment newInstance(String param1) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.category_frgment_layout;
    }

    @Override
    protected void initViews() {


        LinearLayoutManager lm = new LinearLayoutManager(activity);
        rvLeft.setLayoutManager(lm);
        leftAdapter = new CategoryLeftAdapter(activity, list_left);
        rvLeft.setAdapter(leftAdapter);

        VirtualLayoutManager vlayout = new VirtualLayoutManager(activity);
        rvRight.setLayoutManager(vlayout);
        delegateAdapter = new DelegateAdapter(vlayout);
        delegateAdapter.setAdapters(adapterList);
        rvRight.setAdapter(delegateAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        ptrview.setOnPtrListener(GeneralPtrView.PTRTYPE.NONE, new GeneralPtrView.OnPtrListener() {
            @Override
            public void onPtrRefresh() {

            }

            @Override
            public void onPtrLoadMore() {

            }
        });
    }
}
