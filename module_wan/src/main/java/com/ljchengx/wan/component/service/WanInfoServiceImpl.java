package com.ljchengx.wan.component.service;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.ui.fragment.FirstPageFragment;

import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonservice.wan.bean.WanInfo;
import me.jessyan.armscomponent.commonservice.wan.service.WanInfoService;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: com.ljchengx.wan.component.service
 * @ClassName: WanInfoServiceImpl
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 14:27
 */
@Route(path = RouterHub.WAN_SERVICE_WANINFOSERVICE, name = "玩")
public class WanInfoServiceImpl implements WanInfoService{

    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @Override
    public Fragment getWanFragment() {
        return FirstPageFragment.newInstance();
    }
}
