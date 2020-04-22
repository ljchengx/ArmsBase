package com.ljchengx.wan.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.model.entity.ArticleBean;

import java.util.List;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonres.adapter.BaseAdapter;
import me.jessyan.armscomponent.commonres.adapter.BaseViewHolder;

/**
 * @ProjectName: ArmsBase
 * @Package: com.ljchengx.wan.mvp.ui.adapter
 * @ClassName: FirstPageAdapter
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/4/21 19:25
 */
public class FirstPageAdapter extends BaseAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public FirstPageAdapter(int layoutResId, @Nullable List<ArticleBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        Observable.just(item.getAuthor())
                .subscribe(s -> helper.setText(R.id.tv_author, s));
        helper.setText(R.id.tv_lable, item.getSuperChapterName() + ":" + item.getChapterName());
        helper.setText(R.id.tv_lable, item.getSuperChapterName());
        helper.setText(R.id.tx_content, item.getDesc());
        helper.setText(R.id.tv_time, item.getNiceDate());
        helper.setText(R.id.tx_title, item.getTitle());

        if(!ArmsUtils.isEmpty(item.getEnvelopePic())){
            ImageView imageView = helper.getView(R.id.iv_thumbnail);
            Glide.with(mContext).load(item.getEnvelopePic()).into(imageView);
        }


    }
}
