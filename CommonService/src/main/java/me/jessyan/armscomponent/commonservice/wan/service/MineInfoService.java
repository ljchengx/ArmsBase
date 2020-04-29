package me.jessyan.armscomponent.commonservice.wan.service;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @ProjectName: ArmsBase
 * @Package: me.jessyan.armscomponent.commonservice.wan.service
 * @ClassName: MineInfoService
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/4/29 17:32
 */
public interface MineInfoService extends IProvider {
    Fragment getMineFragment();
}
