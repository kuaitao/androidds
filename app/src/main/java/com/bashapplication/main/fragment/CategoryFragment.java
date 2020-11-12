package com.bashapplication.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.category.adapter.CategoryLeftAdapter;
import com.bashapplication.category.adapter.GoodsPicAdapter;
import com.bashapplication.category.bean.CategoryLeftBean;
import com.bashapplication.category.bean.GoodsPicBean;
import com.bashapplication.view.GeneralPtrView;
import com.bashapplication.view.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    @BindView(R.id.ptrview)
    GeneralPtrView ptrview;
    @BindView(R.id.vg_content)
    ViewGroup vgContent;
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.rly_root)
    RelativeLayout rlyRoot;

    private CategoryLeftAdapter leftAdapter;
    private GoodsPicAdapter goodsPicAdapter;
    private List<CategoryLeftBean> list_left = new ArrayList<CategoryLeftBean>();
    private List<GoodsPicBean> goodsPicBeanList = new ArrayList<GoodsPicBean>();

    public static CategoryFragment newInstance(String param1) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.category_frgment_layout;
    }

    @Override
    protected void initViews() {
        titleTitle.setText("商品分类");
        list_left.clear();
        CategoryLeftBean categoryLeftBean = new CategoryLeftBean();
        categoryLeftBean.setName("女士");
        CategoryLeftBean categoryLeftBean2 = new CategoryLeftBean();
        categoryLeftBean2.setName("男士");
        CategoryLeftBean categoryLeftBean3 = new CategoryLeftBean();
        categoryLeftBean3.setName("功能类");
        CategoryLeftBean categoryLeftBean4 = new CategoryLeftBean();
        categoryLeftBean4.setName("配件");
        CategoryLeftBean categoryLeftBean5 = new CategoryLeftBean();
        categoryLeftBean5.setName("配件");

        list_left.add(categoryLeftBean);
        list_left.add(categoryLeftBean2);
        list_left.add(categoryLeftBean3);
        list_left.add(categoryLeftBean4);
        list_left.add(categoryLeftBean5);

        LinearLayoutManager lm = new LinearLayoutManager(activity);
        rvLeft.setLayoutManager(lm);
        leftAdapter = new CategoryLeftAdapter(activity, list_left);
        rvLeft.setAdapter(leftAdapter);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);
        rvRight.setLayoutManager(linearLayoutManager);
        goodsPicAdapter = new GoodsPicAdapter(getActivity(), goodsPicBeanList);
        rvRight.setAdapter(goodsPicAdapter);

        list_left.get(0).setChecked(true);
        leftAdapter.notifyDataSetChanged();
        updateRightData(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        ptrview.setOnPtrListener(GeneralPtrView.PTRTYPE.NONE, new GeneralPtrView.OnPtrListener() {
            @Override
            public void onPtrRefresh() {

            }

            @Override
            public void onPtrLoadMore() {

            }
        });

        leftAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object item, int pos) {
                for (CategoryLeftBean b : list_left) {
                    b.setChecked(false);
                }
                list_left.get(pos).setChecked(true);
                leftAdapter.notifyDataSetChanged();

                goodsPicBeanList.clear();
                goodsPicAdapter.notifyDataSetChanged();

                updateRightData(pos);


            }
        });
    }

    private void updateRightData(int pos) {

        for (int i = 0; i < 20; i++) {
            GoodsPicBean goodsPicBean = new GoodsPicBean();

            switch (pos){
                case  0:
                    goodsPicBean.setName("女士");
                    break;
                case  1:
                    goodsPicBean.setName("男士");
                    break;
                case  2:
                    goodsPicBean.setName("功能");
                    break;
                case  3:
                    goodsPicBean.setName("配件");
                    break;
                case  4:
                    goodsPicBean.setName("配件");
                    break;
            }

            rvRight.scrollToPosition(0);
            goodsPicBeanList.add(goodsPicBean);
        }
        goodsPicAdapter.setNewData(goodsPicBeanList);
    }
}
