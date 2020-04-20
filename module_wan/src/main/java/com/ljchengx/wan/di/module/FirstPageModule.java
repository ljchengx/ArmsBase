package com.ljchengx.wan.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ljchengx.wan.mvp.contract.FirstPageContract;
import com.ljchengx.wan.mvp.model.FirstPageModel;
import com.ljchengx.wan.mvp.model.entity.BannerData;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/08/2020 19:18
 * ================================================
 */
@Module
public abstract class FirstPageModule {

    @Binds
    abstract FirstPageContract.Model bindFirstPageModel(FirstPageModel model);

    @FragmentScope
    @Provides
    static List<BannerData.DataBean> provideList() {
        return new ArrayList<>();
    }
}