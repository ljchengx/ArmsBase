package com.ljchengx.web.app;

import android.app.Application;
import android.content.Context;

import com.jess.arms.base.delegate.AppLifecycles;

import io.reactivex.annotations.NonNull;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by ArmsComponentTemplate on 04/27/2020 17:05
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

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
