package com.ljchengx.wan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.ljchengx.wan.mvp.contract.FirstPageContract;
import com.ljchengx.wan.mvp.model.api.service.WanService;
import com.ljchengx.wan.mvp.model.entity.ArticleBean;
import com.ljchengx.wan.mvp.model.entity.BannerData;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/08/2020 19:18
 * ================================================
 */
@FragmentScope
public class FirstPageModel extends BaseModel implements FirstPageContract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public FirstPageModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BannerData> geBannerList() {
        return mRepositoryManager.obtainRetrofitService(WanService.class).geBannerList();
    }

    @Override
    public Observable<ArticleBean> getArticleList(int page) {
        return mRepositoryManager.obtainRetrofitService(WanService.class).getArticleList(page);
    }
}