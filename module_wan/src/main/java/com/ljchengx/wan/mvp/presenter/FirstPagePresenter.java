package com.ljchengx.wan.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.ljchengx.wan.mvp.contract.FirstPageContract;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/08/2020 19:18
 * ================================================
 */
@FragmentScope
public class FirstPagePresenter extends BasePresenter<FirstPageContract.Model, FirstPageContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public FirstPagePresenter(FirstPageContract.Model model, FirstPageContract.View rootView) {
        super(model, rootView);
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
