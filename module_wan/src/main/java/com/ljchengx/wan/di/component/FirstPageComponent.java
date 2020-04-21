package com.ljchengx.wan.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.ljchengx.wan.di.module.FirstPageArticeModule;
import com.ljchengx.wan.di.module.FirstPageModule;
import com.ljchengx.wan.mvp.contract.FirstPageContract;

import com.jess.arms.di.scope.FragmentScope;
import com.ljchengx.wan.mvp.ui.fragment.FirstPageFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/08/2020 19:18
 * ================================================
 */
@FragmentScope
@Component(modules = {FirstPageModule.class, FirstPageArticeModule.class}, dependencies = AppComponent.class)
public interface FirstPageComponent {
    void inject(FirstPageFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FirstPageComponent.Builder view(FirstPageContract.View view);

        FirstPageComponent.Builder appComponent(AppComponent appComponent);

        FirstPageComponent build();
    }
}