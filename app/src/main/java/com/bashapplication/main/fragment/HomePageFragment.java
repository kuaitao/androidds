package com.bashapplication.main.fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.home.bean.ArticleGroupBean;
import com.bashapplication.home.fragment.HomeGoodsListFragment;
import com.bashapplication.utils.DensityUtil;
import com.bashapplication.view.ViewPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.tl_entrance_tab)
    SlidingTabLayout tlEntranceTab;
    @BindView(R.id.vp_entrance)
    ViewPager vpEntrance;

    private List<ArticleGroupBean> data = new ArrayList<>();

    public static HomePageFragment newInstance(String param1) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutViewId() {
        return R.layout.home_page_layout;
    }

    @Override
    protected void initViews() {

        ArticleGroupBean articleGroupBean1 = new ArticleGroupBean();
        articleGroupBean1.setId("100");
        articleGroupBean1.setName("杂谈");

        ArticleGroupBean articleGroupBean2 = new ArticleGroupBean();
        articleGroupBean2.setId("100");
        articleGroupBean2.setName("说说");

        ArticleGroupBean articleGroupBean3 = new ArticleGroupBean();
        articleGroupBean3.setId("116");
        articleGroupBean3.setName("高考");

        ArticleGroupBean articleGroupBean4 = new ArticleGroupBean();
        articleGroupBean4.setId("117");
        articleGroupBean4.setName("考研");

        ArticleGroupBean articleGroupBean5 = new ArticleGroupBean();
        articleGroupBean5.setId("118");
        articleGroupBean5.setName("出国");

        ArticleGroupBean articleGroupBean6 = new ArticleGroupBean();
        articleGroupBean6.setId("119");
        articleGroupBean6.setName("创业");

        ArticleGroupBean articleGroupBean7 = new ArticleGroupBean();
        articleGroupBean7.setId("120");
        articleGroupBean7.setName("求职");

        ArticleGroupBean articleGroupBean8 = new ArticleGroupBean();
        articleGroupBean8.setId("121");
        articleGroupBean8.setName("公考");

        data.add(articleGroupBean1);
        data.add(articleGroupBean2);
        data.add(articleGroupBean3);
        data.add(articleGroupBean4);
        data.add(articleGroupBean5);
        data.add(articleGroupBean6);
        data.add(articleGroupBean7);
        data.add(articleGroupBean8);


        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            titles[i] = data.get(i).getName();
        }
        vpEntrance.setCurrentItem(0);
        vpEntrance.setOffscreenPageLimit(1);
        ViewPagerAdapter<HomeGoodsListFragment> homeGoodsListFragmentViewPagerAdapter = new ViewPagerAdapter<>(getChildFragmentManager(), newInstance(HomeGoodsListFragment.class, null), data);
        vpEntrance.setAdapter(homeGoodsListFragmentViewPagerAdapter);


        tlEntranceTab.setViewPager(vpEntrance, titles);

        TextView title = (TextView) (((RelativeLayout) ((LinearLayout) tlEntranceTab.getChildAt(0)).getChildAt(0)).getChildAt(0));
        title.setTextAppearance(getActivity(), R.style.TabLayoutTextStyle);
        //创建对象
        SpannableString textSpan = new SpannableString(title.getText());
        //设置首个字符的字体大小
        textSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(getContext(), 18)), 0, title.getText().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //用textView展示出来
        title.setText(textSpan);



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        tlEntranceTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                for(int i = 0; i < data.size(); i++){
                    if(position == i){
                        setSelectView(position);
                    }else{
                        setReselectView(i);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vpEntrance.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i < data.size(); i++){
                    if(position == i){
                        setSelectView(position);
                    }else{
                        setReselectView(i);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setSelectView(int position) {
        TextView title = (TextView) (((RelativeLayout) ((LinearLayout) tlEntranceTab.getChildAt(0)).getChildAt(position)).getChildAt(0));
        title.setTextAppearance(getActivity(), R.style.TabLayoutTextStyle);
        //创建对象
        SpannableString textSpan = new SpannableString(title.getText());
        //设置首个字符的字体大小
        textSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(getContext(), 18)), 0, title.getText().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //用textView展示出来
        title.setText(textSpan);
    }
    public void setReselectView(int position) {
        TextView title = (TextView) (((RelativeLayout) ((LinearLayout) tlEntranceTab.getChildAt(0)).getChildAt(position)).getChildAt(0));
        title.setTextAppearance(getActivity(), R.style.TabLayoutDefaultStyle);
        //创建对象
        SpannableString textSpan = new SpannableString(title.getText());
        //设置首个字符的字体大小
        textSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(getContext(), 14)), 0, title.getText().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //用textView展示出来
        title.setText(textSpan);
    }
}
