package com.ljchengx.wan.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.contract.FirstPageContract;
import com.ljchengx.wan.mvp.model.FirstPageModel;
import com.ljchengx.wan.mvp.model.entity.ArticleBean;
import com.ljchengx.wan.mvp.model.entity.BannerData;
import com.ljchengx.wan.mvp.ui.adapter.FirstPageAdapter;

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
public abstract class FirstPageArticeModule {

    @FragmentScope
    @Provides
    static List<ArticleBean.DataBean.DatasBean> provideList(){
        return new ArrayList<>();
    }


    @FragmentScope
    @Provides
    static FirstPageAdapter provideFirstPageAdapter(FirstPageContract.View view, List<ArticleBean.DataBean.DatasBean> list) {
        FirstPageAdapter adapter = new FirstPageAdapter(R.layout.fragment_first_page_item, list);
        return adapter;
    }
}