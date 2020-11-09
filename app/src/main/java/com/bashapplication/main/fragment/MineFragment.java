package com.bashapplication.main.fragment;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.mine.GoodsAdapter;
import com.bashapplication.mine.GoodsBean;
import com.bashapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView rv_view;

    private List<GoodsBean> goodsBeanList = new ArrayList<>();
    private GoodsAdapter goodsAdapter;

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

        VirtualLayoutManager vlayout = new VirtualLayoutManager(getContext());
        rv_view.setLayoutManager(vlayout);

        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(2,10);
        goodsAdapter = new GoodsAdapter(getContext(), staggeredGridLayoutHelper){
            @Override
            public void onBindViewHolder(RecyclerViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(getActivity(),168));
                if (position % 2 == 0) {
                    layoutParams.height = Utils.dip2px(getActivity(),232);
                } else {
                    layoutParams.mAspectRatio = 1.0f;
                }
                holder.itemView.setLayoutParams(layoutParams);
            }
        };

        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        adapters.add(goodsAdapter);

        DelegateAdapter delegateAdapter = new DelegateAdapter(vlayout);
        delegateAdapter.setAdapters(adapters);
        rv_view.setAdapter(delegateAdapter);
    }

    @Override
    protected void initData() {

        for (int i = 0; i <10 ; i++) {
            GoodsBean goodsBean;
            if(i%2==0){
                 goodsBean = new GoodsBean(R.mipmap.test1,"秋冬新款纯牛皮包秋冬新款纯牛皮包 秋冬新款纯...","256.20");
            }else{
                goodsBean = new GoodsBean(R.mipmap.test2,"秋冬新款纯牛皮包秋冬新款纯牛皮包 秋冬新款纯...","256.20");

            }
            goodsBeanList.add(goodsBean);
        }
        goodsAdapter.setData(goodsBeanList);

    }

    @Override
    protected void setListener() {

    }
}
