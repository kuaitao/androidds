package com.bashapplication.mine.adapter;

import android.content.Context;

import com.bashapplication.R;
import com.bashapplication.mine.bean.AddressBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AddressListAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    private Context context;

    public AddressListAdapter(Context context, List<AddressBean> listCode) {
        super(R.layout.item_address_list, listCode);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, final AddressBean item) {
        helper.addOnClickListener(R.id.rly_root)
                .addOnClickListener(R.id.iv_detail)
                .setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_phone,item.getPhone())
                .setText(R.id.tv_address,item.getAddres());

        if("gongsi".equals(item.getFlag())){
            helper.setGone(R.id.tv_flag,true).setText(R.id.tv_flag,"公司")
                    .setTextColor(R.id.tv_flag,context.getResources().getColor(R.color.colorff5034))
                    .setBackgroundColor(R.id.tv_flag,context.getResources().getColor(R.color.colorffefec));

        }else if("moren".equals(item.getFlag())){
            helper.setGone(R.id.tv_flag,true).setText(R.id.tv_flag,"默认地址")
              .setTextColor(R.id.tv_flag,context.getResources().getColor(R.color.color3A8FFF))
                    .setBackgroundRes(R.id.tv_flag,R.drawable.shap_c3a8fff_cor2_stroke);
        }else{
            helper.setGone(R.id.tv_flag,false);
        }

    }
}
