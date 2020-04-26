package com.ljchengx.wan.di.module;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.ljchengx.wan.app.WanConstants;
import com.ljchengx.wan.mvp.contract.WanHomeContract;
import com.ljchengx.wan.mvp.model.WanModel;
import com.ljchengx.wan.mvp.model.entity.WxarticleBean;
import com.ljchengx.wan.mvp.ui.adapter.WanHomeAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/11/2020 13:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class MainModule {

    @Binds
    abstract WanHomeContract.Model bindMainModel(WanModel model);

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(WanHomeContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @ActivityScope
    @Provides
    static List<WxarticleBean.DataBean> provideList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideWanHomeAdapter(WanHomeContract.View view, List<WxarticleBean.DataBean> list) {
        WanHomeAdapter adapter = new WanHomeAdapter(list);
        adapter.setOnItemClickListener((DefaultAdapter.OnRecyclerViewItemClickListener<WxarticleBean.DataBean>) (view1, viewType, data, position) -> {
                ARouter.getInstance()
                        .build(RouterHub.WAN_DETAILACTIVITY)
                        .withInt(WanConstants.DETAIL_ID, data.getId())
                        .navigation(view.getActivity());

//            ARouter.getInstance()
//                    .build(RouterHub.WAN_DETAILFRAG
//                    MENT)
//                    .withInt(WanConstants.DETAIL_ID, data.getId())
//                    .navigation(view.getActivity());



        });
        return adapter;
    }
}