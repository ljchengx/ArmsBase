package com.ljchengx.wan.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.ljchengx.wan.di.module.WanDetailModule;
import com.ljchengx.wan.mvp.contract.WanDetailContract;

import com.jess.arms.di.scope.ActivityScope;
import com.ljchengx.wan.mvp.ui.activity.WanDetailActivity;
import com.ljchengx.wan.mvp.ui.activity.WanDetailFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
@ActivityScope
@Component(modules = WanDetailModule.class, dependencies = AppComponent.class)
public interface WanDetailComponent {
    void inject(WanDetailActivity activity);
    void inject(WanDetailFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WanDetailComponent.Builder view(WanDetailContract.View view);

        WanDetailComponent.Builder appComponent(AppComponent appComponent);

        WanDetailComponent build();
    }
}