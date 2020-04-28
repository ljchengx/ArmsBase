package com.ljchengx.wan.mvp.ui.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

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

        if (!StringUtils.isEmpty(item.getAuthor())) {
            helper.getView(R.id.tv_author).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_author, item.getAuthor());
        } else {
            helper.getView(R.id.tv_author).setVisibility(View.GONE);
        }


        String lable = item.getSuperChapterName() + "·" + item.getChapterName();
        helper.setText(R.id.tv_lable, lable);

        if (!StringUtils.isEmpty(item.getDesc())) {
            helper.getView(R.id.tx_content).setVisibility(View.VISIBLE);
            Observable.just(Html.fromHtml(item.getDesc()))
                    .subscribe(s -> helper.setText(R.id.tx_content, s));
        } else {
            helper.getView(R.id.tx_content).setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_time, item.getNiceDate());
        helper.setText(R.id.tx_title, item.getTitle());

        ImageView imageView = helper.getView(R.id.iv_thumbnail);
        if (!StringUtils.isEmpty(item.getEnvelopePic())) {
            imageView.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(item.getEnvelopePic())
                    .resize(100, 100)
                    .into((ImageView) helper.getView(R.id.iv_thumbnail));
        } else {
            imageView.setVisibility(View.GONE);
        }

        if (item.getTags().size() != 0) {
            helper.getView(R.id.tv_isHomeStation).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_isHomeStation, item.getTags().get(0).getName());
        }else{
            helper.getView(R.id.tv_isHomeStation).setVisibility(View.GONE);
        }

        if(item.getNiceDate().contains("前")){
            helper.getView(R.id.iv_new).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.iv_new).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.ll_collect);
    }
}
