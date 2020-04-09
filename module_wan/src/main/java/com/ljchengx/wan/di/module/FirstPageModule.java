package com.ljchengx.wan.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.ljchengx.wan.mvp.contract.FirstPageContract;
import com.ljchengx.wan.mvp.model.FirstPageModel;


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
}