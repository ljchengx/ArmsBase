package com.ljchengx.wan.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R;
import com.ljchengx.wan.R2;
import com.ljchengx.wan.di.component.DaggerFirstPageComponent;
import com.ljchengx.wan.mvp.contract.FirstPageContract;
import com.ljchengx.wan.mvp.model.entity.ArticleBean;
import com.ljchengx.wan.mvp.model.entity.BannerData;
import com.ljchengx.wan.mvp.presenter.FirstPagePresenter;
import com.ljchengx.wan.mvp.ui.adapter.FirstPageAdapter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/08/2020 19:18
 * ================================================
 */
public class FirstPageFragment extends BaseFragment<FirstPagePresenter> implements FirstPageContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.tool_title)
    TextView mToolTitle;
    Unbinder unbinder;
    @BindView(R2.id.banner)
    MZBannerView mBanner;

    int currentPage = 0;
    @BindView(R2.id.rv_list)
    RecyclerView mRvList;

    @Inject
    FirstPageAdapter mAdapter;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static FirstPageFragment newInstance() {
        FirstPageFragment fragment = new FirstPageFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFirstPageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_page, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolTitle.setText("这是Wan首页");
        mPresenter.requestDailyList();
        mPresenter.requestGetArticleList(currentPage);
        initRecyclerView();
        mRvList.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(() -> {
            mPresenter.requestGetArticleList(currentPage);
        }, mRvList);
    }

    private void initRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.start();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void geBannerListSuccess(BannerData bannerData) {
        mBanner.setPages(bannerData.getData(), (MZHolderCreator<BannerViewHolder>) () -> new BannerViewHolder());
        mBanner.start();
    }

    @Override
    public void getArticleListSuccess(ArticleBean articleBean) {
        if (currentPage == 0) {
            mAdapter.setNewData(articleBean.getData().getDatas());
        } else {
            mAdapter.addData(articleBean.getData().getDatas());
        }
        currentPage++;
        mAdapter.loadMoreComplete();
        mAdapter.setEnableLoadMore(mAdapter.getData().size() < articleBean.getData().getTotal());

    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mPresenter.requestGetArticleList(currentPage);
    }

    public static class BannerViewHolder implements MZViewHolder<BannerData.DataBean> {
        private ImageView mImageView;
        private TextView mTextView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.wan_banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            mTextView = view.findViewById(R.id.tv_describe);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerData.DataBean data) {
            Glide.with(context).load(data.getImagePath()).into(mImageView);
            mTextView.setText(data.getTitle());
        }
    }
}
