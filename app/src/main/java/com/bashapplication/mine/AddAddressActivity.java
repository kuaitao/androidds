package com.bashapplication.mine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseActivity;
import com.bashapplication.mine.bean.UserLabelSelectBean;
import com.bashapplication.utils.DensityUtil;
import com.bashapplication.utils.ThreadPoolManager;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * 添加收货地址
 */
public class AddAddressActivity extends BaseActivity {


    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.recyclerview_under)
    FlexboxLayout recyclerviewUnder;

    /**
     * 标签数据
     */
    private List<UserLabelSelectBean> labelist = new ArrayList();
    /**
     * 选中标签
     */
    private List<UserLabelSelectBean> selectLLabelList = new ArrayList();

    @Override
    protected int layoutViewId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initViews() {

        labelist.clear();
        labelist.add(new UserLabelSelectBean("公司"));
        labelist.add(new UserLabelSelectBean("家"));
        labelist.add(new UserLabelSelectBean("学校"));
        labelist.add(new UserLabelSelectBean("其他"));

        for (int i = 0; i < labelist.size(); i++) {
            addChildToFlexboxLayout(labelist.get(i));
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.title_right, R.id.tv_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_right:
                break;
            case R.id.tv_region:
                tvRegion.post(new Runnable() {
                    @Override
                    public void run() {
                        selectAddress();
                    }
                });

                break;
        }
    }


    private  BottomSheetDialog bottomSheetDialog;
    private AddressSelector selector;
    private String cityStr;
    private String countyStr;
    private String provinceStr;
    /**
     * 选择地址
     */
    public void selectAddress() {

        if(bottomSheetDialog != null){
            bottomSheetDialog.dismiss();
        }
        bottomSheetDialog = new BottomSheetDialog(this);
        selector = new AddressSelector(activity);
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                provinceStr = province.name;
                cityStr = city.name;
                countyStr = county.name;
                // blahblahblah
                tvRegion.setText(province.name + " " + city.name + county.name);
                bottomSheetDialog.dismiss();
            }
        });

        selector.setAddressProvider(new AddressProvider() {
            @Override
            public void provideProvinces(AddressReceiver<Province> addressReceiver) {
                ThreadPoolManager.getInstance().addExecuteTask(new Runnable() {
                    @Override
                    public void run() {
//                        String json = (String) SharedPreHelper.getInstance(AddAddressActivity.this).getSharedPreference(SharedPreHelper.SharedAttribute.PROVINCE_KEY, "");
//                        Log.v("json", json);
//                        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
//                        List<Province> lists = gson.fromJson(json, new TypeToken<List<Province>>() {
//                        }.getType());
//                        addressReceiver.send(lists);
                    }
                });

            }

            @Override
            public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {
//                cityReceiver = addressReceiver;
//                BaseRequest request = new BaseRequest();
//                request.setId(provinceId + "");
//                cityPresenter.onCity(request);

            }

            @Override
            public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {
//                countyReceiver = addressReceiver;
//                BaseRequest request = new BaseRequest();
//                request.setId(cityId + "00");
//                countyPresenter.onCounty(request);
            }

            @Override
            public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {
                addressReceiver.send(new ArrayList<>());
            }
        });
        View view = selector.getView();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(500));
        view.setLayoutParams(params);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }


    /**
     * 标签
     */
    private void addChildToFlexboxLayout(final UserLabelSelectBean bean) {
        View view = LayoutInflater.from(this).inflate(R.layout.user_label, null);
        TextView tv = view.findViewById(R.id.txt_content);
        tv.setText(bean.getNameStr());
        view.setTag(bean);
        if (bean.checked) {
            tv.setBackgroundColor(activity.getResources().getColor(R.color.colorffefec));
            tv.setTextColor(activity.getResources().getColor(R.color.colorff5034));
            selectLLabelList.add(bean);

        } else {
            tv.setBackgroundColor(activity.getResources().getColor(R.color.colorf6f6f6));
            tv.setTextColor(activity.getResources().getColor(R.color.color999999));

            if (selectLLabelList.size() > 0) {
                selectLLabelList.remove(bean);
            }

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.checked = true;
                for (UserLabelSelectBean labelBean : labelist) {
                    if (!labelBean.equals(bean)) {
                        labelBean.checked = false;
                    }
                }
                checkLabeel();
            }
        });
        recyclerviewUnder.addView(view);
    }
    private void checkLabeel() {
        recyclerviewUnder.removeAllViews();
        for (UserLabelSelectBean labelBean: labelist) {
            addChildToFlexboxLayout(labelBean);
        }
    }

}