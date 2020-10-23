package com.bashapplication.home.fragment;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bashapplication.R;
import com.bashapplication.bash.BaseFragment;
import com.bashapplication.home.bean.ArticleGroupBean;
import com.bashapplication.home.bean.ForumListBean;
import com.bashapplication.home.contract.ForumListContract;
import com.bashapplication.home.presenter.ForumListPresenter;
import com.bashapplication.login.bean.UserBean;
import com.bashapplication.view.GeneralPtrView;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeGoodsListFragment extends BaseFragment implements ForumListContract.IForumListView {
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.ptrview)
    GeneralPtrView ptrview;
    private List<ForumListBean> mDatas = new ArrayList<ForumListBean>();

    private int page = 1;
    private int pageSize = 20;

    private String groupID = "";
    private ArticleGroupBean groupBean;

    private ForumListPresenter forumListPresenter;

    @Override
    protected int layoutViewId() {
        return R.layout.home_goods_fragment_layout;
    }

    @Override
    protected void initViews() {

        forumListPresenter = (ForumListPresenter) createPresenter(ForumListPresenter.class);
        forumListPresenter.attachView(this);
        groupBean = getArguments().getParcelable(BaseFragment.BEAN_KEY);
        if (groupBean != null) {
            groupID = groupBean.getId();
        }

        Toast.makeText(activity, ""+groupBean.getName(), Toast.LENGTH_SHORT).show();

        page = 1;
    }

    @Override
    protected void initData() {
        HttpParams httpParams =new HttpParams();
        httpParams.put("_page",page+"");
        httpParams.put("_COUNT",pageSize+"");
        httpParams.put("groupId",groupID);
      //  forumListPresenter.onForumList(activity,httpParams);

    }

    @Override
    protected void setListener() {
        ptrview.setOnPtrListener(GeneralPtrView.PTRTYPE.BOTH, new GeneralPtrView.OnPtrListener() {
            @Override
            public void onPtrRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onPtrLoadMore() {
                if(mDatas.size() == 0){
                    ptrview.refreshComplete();
                    return;
                }
                page ++;
                initData();
            }
        });
    }


    @Override
    public void forumListSuccess(UserBean userBean) {

    }

    @Override
    public void forumListError(ApiException apiExc) {

    }
}
