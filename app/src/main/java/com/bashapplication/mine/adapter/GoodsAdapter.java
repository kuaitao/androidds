package com.bashapplication.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bashapplication.R;
import com.bashapplication.mine.bean.GoodsBean;
import com.bashapplication.utils.GlideUtils;
import com.bashapplication.view.DelegateRecyclerAdapter;

public class GoodsAdapter extends DelegateRecyclerAdapter<GoodsBean> {

    private Context mContext;
    private GridLayoutHelper lh;
    public GoodsAdapter(Context context, GridLayoutHelper lh){
        super(context,null);
        this.mContext = context;
        this.lh = lh;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return lh;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_goods;
    }



    @Override
    protected void bindData(RecyclerViewHolder holder, int position, GoodsBean item) {
        LinearLayout lly_goods = holder.findViewById(R.id.lly_goods);
        TextView tv_goods_name = holder.findViewById(R.id.tv_goods_name);
        TextView price = holder.findViewById(R.id.price);
        ImageView iv_goods_pic = holder.findViewById(R.id.iv_goods_pic);


        GlideUtils.setImageSrc(mContext, iv_goods_pic,item.getImg());
        tv_goods_name.setText(item.getName());
        price.setText("¥"+item.getPrice());

        lly_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemViewListener != null){
                    mOnItemViewListener.onItemViewClick(v,item,position);
                }
            }
        });

    }
}
