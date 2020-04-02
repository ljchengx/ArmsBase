package com.ljchengx.wan.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ljchengx.wan.mvp.contract.WanDetailContract;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
@ActivityScope
public class WanDetailPresenter extends BasePresenter<WanDetailContract.Model, WanDetailContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

//    @Inject
//    List<WxarticleDetailBean.DataBean.DatasBean> mDatas;

    @Inject
    public WanDetailPresenter(WanDetailContract.Model model, WanDetailContract.View rootView) {
        super(model, rootView);
    }


    public void requestDailyList(int id, int page) {
        mModel.getDetailList(id, page)
                .subscribeOn(Schedulers.io())
//                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<WxarticleDetailBean>(mErrorHandler) {
                    @Override
                    public void onNext(WxarticleDetailBean wxarticleDetailBean) {
                        if (wxarticleDetailBean.getErrorCode() == 0) {
//                            mDatas.clear();
//                            mDatas.addAll(wxarticleDetailBean.getData().getDatas());
                            mRootView.getDetailListSuccess(wxarticleDetailBean);
                        }

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
