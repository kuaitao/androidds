package com.bashapplication.main.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.mine.AddressListActivity;
import com.bashapplication.mine.PersonalInfoActivity;
import com.bashapplication.mine.UploadNeedsActivity;
import com.bashapplication.mine.adapter.AddressListAdapter;
import com.bashapplication.mine.adapter.GoodsAdapter;
import com.bashapplication.mine.adapter.GoodsBaseAdapter;
import com.bashapplication.mine.bean.GoodsBean;
import com.bashapplication.mine.map.AddressMapActivity;
import com.bashapplication.utils.JumperUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView rv_view;

    private List<GoodsBean> goodsBeanList = new ArrayList<>();
    private GoodsBaseAdapter goodsAdapter;

    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.mine_page_layout;
    }

    @Override
    protected void initViews() {

//        VirtualLayoutManager vlayout = new VirtualLayoutManager(getContext());
//        rv_view.setLayoutManager(vlayout);
//
//        GridLayoutHelper staggeredGridLayoutHelper = new GridLayoutHelper(2);
//        staggeredGridLayoutHelper.setVGap(10);
//        staggeredGridLayoutHelper.setHGap(10);
//        goodsAdapter = new GoodsAdapter(getContext(), staggeredGridLayoutHelper);
//            @Override
//            public void onBindViewHolder(RecyclerViewHolder holder, int position) {
//                super.onBindViewHolder(holder, position);
//                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(),168));
//                if (position % 2 == 0) {
//                    layoutParams.height = Utils.dip2px(getActivity(),232);
//                } else {
//                    layoutParams.mAspectRatio = 1.0f;
//                }
//                holder.itemView.setLayoutParams(layoutParams);
//            }
//        };

//        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
//        adapters.add(goodsAdapter);
//
//        DelegateAdapter delegateAdapter = new DelegateAdapter(vlayout);
//        delegateAdapter.setAdapters(adapters);
//        rv_view.setAdapter(delegateAdapter);


        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity,2);
        rv_view.setLayoutManager(linearLayoutManager);
        goodsAdapter = new GoodsBaseAdapter(activity, goodsBeanList);
        rv_view.setAdapter(goodsAdapter);
    }

    @Override
    protected void initData() {

        for (int i = 0; i < 10; i++) {
            GoodsBean goodsBean;

            goodsBean = new GoodsBean(R.mipmap.test1, "秋冬新款纯牛皮包秋冬新款纯牛皮包 秋冬新款纯...", "256.20");

            goodsBeanList.add(goodsBean);
        }
        goodsAdapter.addData(goodsBeanList);

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.iv_user_head,R.id.lly_address,R.id.lly_browse_recode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head:

                JumperUtils.JumpTo(activity, PersonalInfoActivity.class);
                break;
            case R.id.lly_address:
                JumperUtils.JumpTo(activity,  AddressMapActivity.class);
                break;
            case R.id.lly_browse_recode:
                JumperUtils.JumpTo(activity, UploadNeedsActivity.class);
                break;

        }
    }


}
