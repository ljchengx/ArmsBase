package com.ljchengx.wan.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;

import java.util.List;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonres.adapter.BaseAdapter;
import me.jessyan.armscomponent.commonres.adapter.BaseViewHolder;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: com.ljchengx.wan.mvp.ui.adapter
 * @ClassName: WanHomeAdapter
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 15:59
 */
public class WanDetailAdapter extends BaseAdapter<WxarticleDetailBean.DataBean.DatasBean, BaseViewHolder> {


    public WanDetailAdapter(int layoutResId, @Nullable List<WxarticleDetailBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WxarticleDetailBean.DataBean.DatasBean item) {
        Observable.just(item.getTitle())
                .subscribe(s -> helper.setText(R.id.tv_name, s));

    }

}
