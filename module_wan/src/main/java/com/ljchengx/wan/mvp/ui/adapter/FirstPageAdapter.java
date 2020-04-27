package com.ljchengx.wan.mvp.ui.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ljchengx.wan.R;
import com.ljchengx.wan.mvp.model.entity.ArticleBean;
import com.squareup.picasso.Picasso;


import java.util.List;

import io.reactivex.Observable;
import me.jessyan.armscomponent.commonres.adapter.BaseAdapter;
import me.jessyan.armscomponent.commonres.adapter.BaseViewHolder;
import me.jessyan.armscomponent.commonres.utils.StringUtils;

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

        String lable = item.getSuperChapterName() + "·" + item.getChapterName();
        helper.setText(R.id.tv_lable, lable);

        if(!StringUtils.isEmpty(item.getDesc())){
            helper.getView(R.id.tx_content).setVisibility(View.VISIBLE);
            Observable.just(Html.fromHtml(item.getDesc()))
                    .subscribe(s -> helper.setText(R.id.tx_content, s));
        }
        helper.setText(R.id.tv_time, item.getNiceDate());
        helper.setText(R.id.tx_title, item.getTitle());

        ImageView imageView = helper.getView(R.id.iv_thumbnail);
        if(!StringUtils.isEmpty(item.getEnvelopePic())){
            imageView.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(item.getEnvelopePic())
                    .resize(100, 100)
                    .into((ImageView) helper.getView(R.id.iv_thumbnail));
        }else {
            imageView.setVisibility(View.GONE);
        }


    }
}
