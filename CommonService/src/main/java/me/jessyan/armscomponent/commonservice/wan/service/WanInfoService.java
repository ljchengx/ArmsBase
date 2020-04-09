package me.jessyan.armscomponent.commonservice.wan.service;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

import me.jessyan.armscomponent.commonservice.wan.bean.WanInfo;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: me.jessyan.armscomponent.commonservice.wan.service
 * @ClassName: WanInfoService
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 14:20
 */
public interface WanInfoService extends IProvider {
    Fragment getWanFragment();
}
