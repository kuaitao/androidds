package com.bashapplication.category.adapter;

import android.content.Context;

import com.bashapplication.R;
import com.bashapplication.category.bean.GoodsPicBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsPicAdapter extends BaseQuickAdapter<GoodsPicBean, BaseViewHolder> {

    private Context context;

    public GoodsPicAdapter(Context context, List<GoodsPicBean> listCode) {
        super(R.layout.item_goods_pic, listCode);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsPicBean item) {
        helper.addOnClickListener(R.id.iv_goods_pic).setBackgroundRes(R.id.iv_goods_pic,R.mipmap.test1);

    }
}
