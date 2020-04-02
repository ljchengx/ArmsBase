package com.ljchengx.wan.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.model.entity.WxarticleBean;
import com.ljchengx.wan.mvp.ui.holder.WanHomeItemHolder;

import java.util.List;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: com.ljchengx.wan.mvp.ui.adapter
 * @ClassName: WanHomeAdapter
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 15:59
 */
public class WanHomeAdapter extends DefaultAdapter<WxarticleBean.DataBean>{

    public WanHomeAdapter(List<WxarticleBean.DataBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<WxarticleBean.DataBean> getHolder(View v, int viewType) {
        return new WanHomeItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.wan_recycle_list;
    }
}
