package com.bashapplication.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.mine.adapter.UploadGodsPicAdapter;
import com.bashapplication.utils.GlideUtils;
import com.bashapplication.utils.PictureSelectUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 上传需求
 */
public class UploadNeedsActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    //图片最大值
    private int MAX_SELECTED = 3;
    private String addPic = "add";
    private List<LocalMedia> picList =new ArrayList<>();
    private List<LocalMedia> previewList =new ArrayList<>();
    private UploadGodsPicAdapter uploadGodsPicAdapter;


    @Override
    protected int layoutViewId() {
        return R.layout.activity_upload_needs;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.upload_needs);

        if(picList.size()==0){
            LocalMedia localMedia = new LocalMedia();
            localMedia.setCompressPath(addPic);
            picList.add(localMedia);
        }
        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity, 3);
        recyclerview.setLayoutManager(linearLayoutManager);
        uploadGodsPicAdapter = new UploadGodsPicAdapter(activity, picList);
        uploadGodsPicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LocalMedia itemStr =(LocalMedia) adapter.getItem(position);

                switch (view.getId()) {
                    case R.id.iv_pic:

                        if(itemStr.getCompressPath().equals(addPic)){

                            int selectNum;
                            if(picList.size()>0){
                                 selectNum = MAX_SELECTED -picList.size()+1;
                            }else{
                                selectNum = MAX_SELECTED;
                            }

                            PictureSelectUtil.selecPictureNoCut(selectNum,activity);
                        }else{

                            previewList.clear();
                            previewList.addAll(picList);
                            previewList.remove(previewList.size()-1);
                            PictureSelectUtil.previewPic(activity,position,previewList);
                        }

                        break;
                    case R.id.iv_del:


                        picList.remove(itemStr);
                        uploadGodsPicAdapter.notifyItemRemoved(position);
                        uploadGodsPicAdapter.notifyItemRangeChanged(position, picList.size());


                        break;
                    default:
                }
            }
        });
        recyclerview.setAdapter(uploadGodsPicAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);


                    for (int i = 0; i < picList.size(); i++) {

                        if(picList.get(i).getCompressPath().equals(addPic)){
                            picList.remove(i);
                        }

                    }

                    picList.addAll(selectList);

                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setCompressPath(addPic);
                    picList.add(localMedia);

                    uploadGodsPicAdapter.notifyDataSetChanged();

                    break;
                default:
                    break;
            }
        }
    }

}