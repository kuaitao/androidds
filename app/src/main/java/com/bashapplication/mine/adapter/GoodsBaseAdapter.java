package com.bashapplication.mine.adapter;

import android.content.Context;

import com.bashapplication.R;
import com.bashapplication.mine.bean.GoodsBean;
import com.bashapplication.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsBaseAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    private Context context;

    public GoodsBaseAdapter(Context context, List<GoodsBean> listCode) {
        super(R.layout.item_goods, listCode);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        helper.addOnClickListener(R.id.lly_goods)
                .setText(R.id.tv_goods_name,item.getName())
                .setText(R.id.price,"¥"+item.getPrice());

        GlideUtils.setImageSrc(mContext, helper.getView(R.id.iv_goods_pic),item.getImg());

    }
}
