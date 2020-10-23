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

        tv_lable_title.setText(item.getName());

        if(item.isChecked()){

            tv_lable_title.setTextColor(ctx.getResources().getColor(R.color.color222427));
            tv_lable_title.setTextSize(16);
            tv_lable_title.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        }else{

            tv_lable_title.setTextColor(ctx.getResources().getColor(R.color.color77808b));
            tv_lable_title.setTextSize(14);
            tv_lable_title.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
        }

    }

}
