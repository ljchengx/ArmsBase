package me.jessyan.armscomponent.commonservice.wan.bean;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: me.jessyan.armscomponent.commonservice.wan.bean
 * @ClassName: WanInfo
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 14:21
 */
public class WanInfo {
    private String name;

    public WanInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
