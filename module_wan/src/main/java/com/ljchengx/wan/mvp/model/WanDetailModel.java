package com.ljchengx.wan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.ljchengx.wan.mvp.contract.WanDetailContract;
import com.ljchengx.wan.mvp.model.api.service.WanService;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
@ActivityScope
public class WanDetailModel extends BaseModel implements WanDetailContract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WanDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<WxarticleDetailBean> getDetailList(int id, int page) {
        return mRepositoryManager.obtainRetrofitService(WanService.class).getDetailList(id,page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


}