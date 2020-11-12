package com.bashapplication.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.home.bean.ShareBannerBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ShareBeanChildAdapter extends BannerAdapter<ShareBannerBean, ShareBeanChildAdapter.BannerViewHolder> {

    private Context context;

    public ShareBeanChildAdapter(Context c, List<ShareBannerBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
        this.context = c;
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_child_banner, null, false);
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        inflate.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerViewHolder(inflate);

    }

    @Override
    public void onBindView(BannerViewHolder holder, ShareBannerBean data, int position, int size) {
        //holder.imageView.setImageResource(data.imageRes);
    }


    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imv_pic;
        public CardView cardview;

        public BannerViewHolder(View view) {
            super(view);
            imv_pic = view.findViewById(R.id.imv_pic);
            cardview = view.findViewById(R.id.cardview);
        }
    }


}
