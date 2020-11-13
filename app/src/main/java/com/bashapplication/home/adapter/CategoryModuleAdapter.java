package com.bashapplication.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bashapplication.R;
import com.bashapplication.view.DelegateRecyclerAdapter;
import com.gcssloop.widget.RCImageView;

public class CategoryModuleAdapter extends DelegateRecyclerAdapter<TestBean1> {

    private Context context;
    GridLayoutHelper layoutHelper;

    private LifecycleOwner lifecycleOwner;


    public CategoryModuleAdapter(Context c, GridLayoutHelper lh, LifecycleOwner lifecycleOwner) {
        super(c, null);
        this.context = c;
        this.layoutHelper = lh;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public GridLayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }



    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_category_module;
    }


    @Override
    protected void bindData(DelegateRecyclerAdapter.RecyclerViewHolder holder, int position, TestBean1 item) {

        TextView tv_name = holder.findViewById(R.id.tv_name);
        TextView tv_detail = holder.findViewById(R.id.tv_detail);
        RCImageView iv_pic = holder.findViewById(R.id.iv_pic);
        RelativeLayout rly_root = holder.findViewById(R.id.rly_root);

        tv_name.setText(item.getName());
        tv_detail.setText(item.getDes());
        iv_pic.setBackgroundResource(item.getPic());
        rly_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemViewListener != null){
                    mOnItemViewListener.onItemViewClick(v,item,position);
                }
            }
        });


    }
}
