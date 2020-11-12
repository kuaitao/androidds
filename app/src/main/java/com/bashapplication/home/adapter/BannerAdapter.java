package com.bashapplication.home.adapter;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.android.vlayout.LayoutHelper;
import com.bashapplication.R;
import com.bashapplication.home.bean.ShareBannerBean;
import com.bashapplication.utils.GlideUtils;
import com.bashapplication.view.DelegateRecyclerAdapter;
import com.youth.banner.Banner;

import java.util.List;

public class BannerAdapter extends DelegateRecyclerAdapter<List<ShareBannerBean>> {
    private Context context;
    LayoutHelper layoutHelper;

    private LifecycleOwner lifecycleOwner;
    private int type = 0;

    public BannerAdapter(Context c, LayoutHelper lh, LifecycleOwner lifecycleOwner, int type) {
        super(c, null);
        this.context = c;
        this.layoutHelper = lh;
        this.lifecycleOwner = lifecycleOwner;
        this.type = type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    public void setBannerData(List<ShareBannerBean> beans) {
        mItems.clear();
        if (beans == null || beans.size() <= 0) {

        } else {
            mItems.add(beans);
        }
        notifyDataSetChanged();
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.banner_share;
    }


    @Override
    protected void bindData(DelegateRecyclerAdapter.RecyclerViewHolder holder, int position, List<ShareBannerBean> item) {
        //L.v("mItem.size", mItems.size());
        Banner banner = holder.findViewById(R.id.banner);

        banner.setAdapter(new ShareBeanChildAdapter(context, item) {
            @Override
            public void onBindView(BannerViewHolder holder, ShareBannerBean data, int position, int size) {
                GlideUtils.setImageSrc(mContext, holder.imv_pic, data.getUrl());

                holder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        }).addBannerLifecycleObserver(lifecycleOwner);//添加生命周期观察者


    }
}
