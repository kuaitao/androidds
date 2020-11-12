package com.bashapplication.mine.adapter;

import android.content.Context;

import com.bashapplication.R;
import com.bashapplication.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class UploadGodsPicAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {


    private Context context;

    private List<LocalMedia> listCode;
    public UploadGodsPicAdapter(Context context, List<LocalMedia> listCode) {
        super(R.layout.item_upload_pic, listCode);
        this.context = context;
        this.listCode = listCode;

    }

    @Override
    protected void convert(BaseViewHolder helper, final LocalMedia item) {
        helper.addOnClickListener(R.id.iv_del)
                .addOnClickListener(R.id.iv_pic);

        if(item.getCompressPath().equals("add")){
            helper.setImageResource(R.id.iv_pic,R.mipmap.photo_icon_2).setGone(R.id.iv_del,false);
        }else{
            GlideUtils.setImageSrc(context, helper.getView(R.id.iv_pic), item.getCompressPath());
            helper.setGone(R.id.iv_del,true);
        }



    }
    @Override
    public int getItemCount() {
        return listCode.size()>3?3:listCode.size();
    }

}
