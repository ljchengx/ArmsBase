package com.ljchengx.wan.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.ArmsUtils;

import com.ljchengx.wan.BuildConfig;
import com.ljchengx.wan.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.ljchengx.wan.mvp.model.api.Api.WAN_DOMAIN_NAME;
import static com.ljchengx.wan.mvp.model.api.Api.WAn_DOMAIN;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by ArmsComponentTemplate on 03/11/2020 13:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/ArmsComponent">Star me</a>
 * <a href="https://github.com/JessYanCoding/ArmsComponent/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/ArmsComponent-Template">模版请保持更新</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

        RetrofitUrlManager.getInstance().putDomain(WAN_DOMAIN_NAME,WAn_DOMAIN);
        //当所有模块集成到宿主 App 时, 在 App 中已经执行了以下代码

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
