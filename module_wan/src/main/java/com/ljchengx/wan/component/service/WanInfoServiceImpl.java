package com.ljchengx.wan.component.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R;

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
public class WanInfoServiceImpl implements WanInfoService {

    private Context mContext;

    @Override
    public WanInfo getInfo() {
        return new WanInfo(ArmsUtils.getString(mContext, R.string.public_name_wan));
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
