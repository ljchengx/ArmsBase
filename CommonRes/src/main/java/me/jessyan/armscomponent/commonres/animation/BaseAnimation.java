package me.jessyan.armscomponent.commonres.animation;

import android.animation.Animator;
import android.view.View;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: me.jessyan.armscomponent.commonres.animation
 * @ClassName: BaseAnimation
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/4/2 10:53
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);
}
