package com.ljchengx.mine.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.ljchengx.mine.mvp.contract.MineContract;
import com.ljchengx.mine.mvp.model.MineModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/29/2020 17:22
 * ================================================
 */
@Module
public abstract class MineModule {

    @Binds
    abstract MineContract.Model bindMineModel(MineModel model);
}