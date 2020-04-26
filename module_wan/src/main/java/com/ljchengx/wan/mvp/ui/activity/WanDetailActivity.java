package com.ljchengx.wan.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R;
import com.ljchengx.wan.R2;
import com.ljchengx.wan.app.WanConstants;
import com.ljchengx.wan.di.component.DaggerWanDetailComponent;
import com.ljchengx.wan.mvp.contract.WanDetailContract;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;
import com.ljchengx.wan.mvp.presenter.WanDetailPresenter;
import com.ljchengx.wan.mvp.ui.adapter.WanDetailAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
@Route(path = RouterHub.WAN_DETAILACTIVITY)
public class WanDetailActivity extends BaseActivity<WanDetailPresenter> implements WanDetailContract.View, SwipeRefreshLayout.OnRefreshListener{


    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    private int detail_id = 0;

    private int currentPage = 1;


    @Inject
    WanDetailAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWanDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_wan_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detail_id = getIntent().getIntExtra(WanConstants.DETAIL_ID, 0);
        initRecyclerView();
        mPresenter.requestDailyList(detail_id, currentPage);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(() -> {
            currentPage++;
            mPresenter.requestDailyList(detail_id, currentPage);
        }, mRecyclerView);
    }

    private void initRecyclerView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void getDetailListSuccess(WxarticleDetailBean wxarticleDetailBean) {

        if (currentPage == 1) {
            mAdapter.setNewData(wxarticleDetailBean.getData().getDatas());
        } else {
            mAdapter.addData(wxarticleDetailBean.getData().getDatas());
        }
        currentPage++;
        mAdapter.loadMoreComplete();
        mAdapter.setEnableLoadMore(mAdapter.getData().size() < wxarticleDetailBean.getData().getTotal());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecyclerView);
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mPresenter.requestDailyList(detail_id, currentPage);
    }
}
