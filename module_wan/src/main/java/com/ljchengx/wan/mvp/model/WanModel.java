package com.ljchengx.wan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.ljchengx.wan.mvp.contract.WanHomeContract;
import com.ljchengx.wan.mvp.model.api.service.WanService;
import com.ljchengx.wan.mvp.model.entity.WxarticleBean;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/11/2020 13:51
 * ================================================
 */
@ActivityScope
public class WanModel extends BaseModel implements WanHomeContract.Model{
    @Inject
    public WanModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<WxarticleBean> geWxarticleList() {
        return mRepositoryManager.obtainRetrofitService(WanService.class).geWxarticleList();
    }
}