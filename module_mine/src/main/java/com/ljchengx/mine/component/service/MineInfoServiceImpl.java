package com.ljchengx.mine.component.service;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ljchengx.mine.mvp.ui.fragment.MineFragment;

import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonservice.wan.service.MineInfoService;

/**
 * @ProjectName: ArmsBase
 * @Package: com.ljchengx.web.component.service
 * @ClassName: MineInfoServiceImpl
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/4/29 17:47
 */
@Route(path = RouterHub.MINE_SERVICE_MINEINFOSERVICE, name = "个人")
public class MineInfoServiceImpl implements MineInfoService{

    private Context mContext;

    @Override
    public Fragment getMineFragment() {
        return MineFragment.newInstance();
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
