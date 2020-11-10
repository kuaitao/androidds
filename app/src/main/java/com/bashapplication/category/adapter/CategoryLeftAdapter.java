package com.bashapplication.category.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bashapplication.R;
import com.bashapplication.category.bean.CategoryLeftBean;
import com.bashapplication.view.RecyclerAdapter;

import java.util.List;

public class CategoryLeftAdapter extends RecyclerAdapter<CategoryLeftBean> {

    private Context ctx;
    public CategoryLeftAdapter(Context ctx, List list) {
        super(ctx, list);
        this.ctx =ctx;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_category_left;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, final CategoryLeftBean item) {
        TextView tv_lable_title = holder.findViewById(R.id.tv_lable_title);
        View view_select = holder.findViewById(R.id.view_select);
        tv_lable_title.setText(item.getName());

        if(item.isChecked()){

            tv_lable_title.setTextColor(ctx.getResources().getColor(R.color.colorff5034));
            tv_lable_title.setTextSize(14);
            tv_lable_title.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            view_select.setVisibility(View.VISIBLE);
        }else{

            tv_lable_title.setTextColor(ctx.getResources().getColor(R.color.color666666));
            tv_lable_title.setTextSize(14);
            tv_lable_title.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
            view_select.setVisibility(View.GONE);
        }

    }

}
