package com.bashapplication.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.utils.GlideUtils;
import com.bashapplication.utils.PictureSelectUtil;
import com.bashapplication.view.SexSelectDialog;
import com.gcssloop.widget.RCImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人信心
 */
public class PersonalInfoActivity extends BaseActivity implements SexSelectDialog.OnSexSelectListener {

    @BindView(R.id.riv_item_head)
    RCImageView rivItemHead;
    @BindView(R.id.tv_platform_accounts)
    TextView tvPlatformAccounts;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    private SexSelectDialog sexSelectDialog;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_personel_info;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.personal_info);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.riv_item_head, R.id.lly_platform_accounts, R.id.lly_nickname_lable, R.id.lly_sex_lable, R.id.lly_birthday_lable})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_item_head:
                //头像
                PictureSelectUtil.selecPicture(1,activity);
                break;
            case R.id.lly_platform_accounts:
                break;
            case R.id.lly_nickname_lable:
                break;
            case R.id.lly_sex_lable:
                //性别
                showSexDialog();
                break;
            case R.id.lly_birthday_lable:
                break;
        }
    }



    private void showSexDialog() {
        if (sexSelectDialog == null) {
            sexSelectDialog = new SexSelectDialog(this);
        }
        sexSelectDialog.setSexSelectListener(this);
        sexSelectDialog.show();

    }

    @Override
    public void onSexResult(String sex) {
        tvSex.setText(sex);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    GlideUtils.setImageSrc(activity,rivItemHead,selectList.get(0).getCutPath());
                    break;
                default:
                    break;
            }
        }
    }
}