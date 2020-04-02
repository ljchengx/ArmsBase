package com.ljchengx.wan.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.contract.WanDetailContract;
import com.ljchengx.wan.mvp.model.WanDetailModel;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;
import com.ljchengx.wan.mvp.ui.adapter.WanDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
@Module
public abstract class WanDetailModule {

    @Binds
    abstract WanDetailContract.Model bindWanDetailModel(WanDetailModel model);


    @ActivityScope
    @Provides
    static List<WxarticleDetailBean.DataBean.DatasBean> provideList() {
        return new ArrayList<>();
    }


    @ActivityScope
    @Provides
    static WanDetailAdapter provideWanHomeAdapter(WanDetailContract.View view, List<WxarticleDetailBean.DataBean.DatasBean> list) {
        WanDetailAdapter adapter = new WanDetailAdapter(R.layout.wan_recycle_list, list);
        return adapter;
    }
}