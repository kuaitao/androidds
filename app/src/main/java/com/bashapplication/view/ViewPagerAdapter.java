package com.bashapplication.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.bashapplication.bash.BaseFragment;
import com.bashapplication.home.bean.ArticleGroupBean;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter<T> extends FragmentPagerAdapter {

    private List<T> listDatas = new ArrayList<>();
    private Object frament;

    @Override
    public int getCount() {
        return listDatas != null ? listDatas.size() : 0;
    }

    public ViewPagerAdapter(FragmentManager fm, Object f, List<?> l) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listDatas = (List<T>) l;
        this.frament  = f;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        Parcelable p = (Parcelable) listDatas.get(position);
        b.putParcelable(BaseFragment.BEAN_KEY,p);
        return BaseFragment.newInstance(frament.getClass(),b);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        Parcelable item = (Parcelable) listDatas.get(position);
        if(item instanceof ArticleGroupBean){
            ArticleGroupBean bean = (ArticleGroupBean) item;
            return bean.getName();
        }
        return "";
    }
}
