/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jessyan.armscomponent.app.mvp.ui.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.animation.RotateAnimation;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import me.jessyan.armscomponent.app.R;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonservice.wan.service.WanInfoService;

/**
 * ================================================
 * 宿主 App 的主页
 *
 * @see <a href="https://github.com/JessYanCoding/ArmsComponent/wiki">ArmsComponent wiki 官方文档</a>
 * Created by JessYan on 19/04/2018 16:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {


    @BindView(R.id.bbl)
    BottomBarLayout bbl;

    @Autowired(name = RouterHub.WAN_SERVICE_WANINFOSERVICE)
    WanInfoService mWanInfoService;

    private RotateAnimation mRotateAnimation;

    private List<Fragment> mFragmentList = new ArrayList<>();

    private long mPressedTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        //这里想展示组件向外提供服务的功能, 模拟下组件向宿主提供一些必要的信息, 这里为了简单就直接返回本地数据不请求网络了
//        loadZhihuInfo();
//        loadGankInfo();
//        loadGoldInfo();
        loadWanInfo();


        changeFragment(0);
        initListener();

    }

    private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentList.get(currentPosition));
        transaction.commit();
    }

    private void initListener() {
        bbl.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
                changeFragment(currentPosition);

                if (currentPosition == 0) {
                    //如果是第一个，即首页
                    if (previousPosition == currentPosition) {
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        if (mRotateAnimation != null && !mRotateAnimation.hasEnded()){
                            //如果当前动画正在执行
                            return;
                        }

//                        bottomBarItem.setSelectedIcon(R.mipmap.tab_loading);//更换成加载图标 setResId
//
//                        //播放旋转动画
//                        if (mRotateAnimation == null) {
//                            mRotateAnimation = new RotateAnimation(0, 360,
//                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                                    0.5f);
//                            mRotateAnimation.setDuration(800);
//                            mRotateAnimation.setRepeatCount(-1);
//                        }
//                        ImageView bottomImageView = bottomBarItem.getImageView();
//                        bottomImageView.setAnimation(mRotateAnimation);
//                        bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画
//
//                        //模拟数据刷新完毕
//                        mHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                boolean tabNotChanged = mBottomBarLayout.getCurrentItem() == currentPosition; //是否还停留在当前页签
//                                bottomBarItem.setSelectedIcon(R.mipmap.tab_home_selected);//更换成首页原来选中图标
//                                cancelTabLoading(bottomBarItem);
//                            }
//                        }, 3000);
//                        return;
                    }
                }
            }
        });
    }

    private void loadZhihuInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
//        if (mZhihuInfoService == null) {
//            mZhihuButton.setEnabled(false);
//            return;
//        }
//        mZhihuButton.setText(mZhihuInfoService.getInfo().getName());
    }

    private void loadGankInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
//        if (mGankInfoService == null) {
//            mGankButton.setEnabled(false);
//            return;
//        }
//        mGankButton.setText(mGankInfoService.getInfo().getName());
    }

    private void loadGoldInfo() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
//        if (mGoldInfoService == null) {
//            mGoldButton.setEnabled(false);
//            return;
//        }
//        mGoldButton.setText(mGoldInfoService.getInfo().getName());
    }

    private void loadWanInfo() {
//        if (mWanInfoService == null) {
//            btWan.setEnabled(false);
//            return;
//        }
//        btWan.setText(mWanInfoService.getInfo().getName());
        mFragmentList.add(mWanInfoService.getWanFragment());

    }

    @Override
    public void onBackPressed() {
        //获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            ArmsUtils.makeText(getApplicationContext(),
                    "再按一次退出" + ArmsUtils.getString(getApplicationContext(), R.string.public_app_name));
            mPressedTime = mNowTime;
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
