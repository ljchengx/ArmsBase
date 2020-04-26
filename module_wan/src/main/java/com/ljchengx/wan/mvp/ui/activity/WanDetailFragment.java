package com.ljchengx.wan.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
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
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WanDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@Route(path = RouterHub.WAN_DETAILFRAGMENT)
public class WanDetailFragment extends BaseFragment<WanDetailPresenter> implements WanDetailContract.View, SwipeRefreshLayout.OnRefreshListener{


    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    private int detail_id = 0;

    private int currentPage = 1;

    @Inject
    WanDetailAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerWanDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_wan_detail, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detail_id = getActivity().getIntent().getIntExtra(WanConstants.DETAIL_ID, 0);
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
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mPresenter.requestDailyList(detail_id, currentPage);
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
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
