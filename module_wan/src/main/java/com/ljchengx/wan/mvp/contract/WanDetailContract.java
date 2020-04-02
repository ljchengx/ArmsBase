package com.ljchengx.wan.mvp.contract;


import android.app.Activity;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 03/18/2020 14:47
 * ================================================
 */
public interface WanDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Activity getActivity();
        void getDetailListSuccess(WxarticleDetailBean wxarticleDetailBean);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<WxarticleDetailBean> getDetailList(int id, int page);
    }
}
