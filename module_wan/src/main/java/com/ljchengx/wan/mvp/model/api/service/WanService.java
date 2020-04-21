package com.ljchengx.wan.mvp.model.api.service;

import com.ljchengx.wan.mvp.model.entity.ArticleBean;
import com.ljchengx.wan.mvp.model.entity.BannerData;
import com.ljchengx.wan.mvp.model.entity.WxarticleBean;
import com.ljchengx.wan.mvp.model.entity.WxarticleDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.ljchengx.wan.mvp.model.api.Api.WAN_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @ProjectName: ArmsComponent-master
 * @Package: com.ljchengx.wan.mvp.model.api.service
 * @ClassName: WanService
 * @Description: 作用描述
 * @Author: ljcheng
 * @CreateDate: 2020/3/11 14:56
 */
public interface WanService {
    @Headers({DOMAIN_NAME_HEADER + WAN_DOMAIN_NAME})
    @GET("/wxarticle/chapters/json")
    Observable<WxarticleBean> geWxarticleList();


    @Headers({DOMAIN_NAME_HEADER + WAN_DOMAIN_NAME})
    @GET("/wxarticle/list/{id}/{page}/json")
    Observable<WxarticleDetailBean> getDetailList(
            @Path("id") int id,
            @Path("page") int page
    );

    @Headers({DOMAIN_NAME_HEADER + WAN_DOMAIN_NAME})
    @GET("/banner/json")
    Observable<BannerData> geBannerList(
    );

    @Headers({DOMAIN_NAME_HEADER + WAN_DOMAIN_NAME})
    @GET("/article/list/{page}/json")
    Observable<ArticleBean> getArticleList(
            @Path("page") int page
    );

}
