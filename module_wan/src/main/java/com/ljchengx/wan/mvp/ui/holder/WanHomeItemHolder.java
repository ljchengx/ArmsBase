package com.ljchengx.wan.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R2;
import com.ljchengx.wan.mvp.model.entity.WxarticleBean;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: com.ljchengx.wan.mvp.ui.holder
 * @ClassName: WanHomeItemHolder
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 16:01
 */
public class WanHomeItemHolder extends BaseHolder<WxarticleBean.DataBean>{


    @BindView(R2.id.tv_name)
    TextView mName;
    private AppComponent mAppComponent;

    public WanHomeItemHolder(View itemView) {
        super(itemView);
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
    }

    @Override
    public void setData(WxarticleBean.DataBean data, int position) {
        Observable.just(data.getName())
                .subscribe(s -> mName.setText(s));
    }
}
