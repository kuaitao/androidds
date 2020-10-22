package com.bashapplication.bash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.bashapplication.utils.InputMethodUtils;
import com.bashapplication.view.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView<T>,CustomAdapt{

    protected FragmentActivity activity;
    protected View rootView;
    private Unbinder mUnbinder;
    protected boolean isViewInit;
    protected boolean isDataInit;
    public Fragment fragmentSelf;


    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";


    protected List<T> mPresenterList = new ArrayList<>();

    protected BasePresenter createPresenter(Class presentClass) {
        try {
            Object object = presentClass.newInstance();
            T instancePresenter = (T) object;
            mPresenterList.add(instancePresenter);
            return instancePresenter;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        InputMethodUtils.hideSoftInput(getActivity());
        solveOverlap(savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentSelf = this;
        activity = getActivity();
        isViewInit = true;
        initViews();
        initData();
        setListener();
        lazyLoad();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }


    protected abstract int layoutViewId();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract void setListener();

    private void lazyLoad() {
        if (getUserVisibleHint() && isViewInit && !isDataInit) {
            onLazyLoad();
            isDataInit = true;
        }
    }

    protected void onLazyLoad() {
    }



    private void solveOverlap(@Nullable Bundle savedInstanceState) {//解决重叠 非必现的bug无法确认是否有效
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    public boolean isFastTwiceClick(View view) {
        return NoDoubleClickListener.isFastTwiceClick(view);
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }

    @Override
    public void onDestroyView() {

        for (T item : mPresenterList) {
            if (item != null)
                item.deatchView();
        }

        super.onDestroyView();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }

        InputMethodUtils.hideSoftInput(activity);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

}