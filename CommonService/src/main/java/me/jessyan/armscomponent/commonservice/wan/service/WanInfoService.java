package me.jessyan.armscomponent.commonservice.wan.service;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

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
