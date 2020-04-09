package com.ljchengx.wan.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * @ProjectName: ArmsBase
 * @Package: com.ljchengx.wan.mvp.contract
 * @ClassName: FirstPageContract
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/4/8 19:19
 */
public interface FirstPageContract {

    interface View extends IView {
        Activity getActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
    }
}
