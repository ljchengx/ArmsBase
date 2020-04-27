package com.ljchengx.web;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.WEB_WEBACTIVITY)
public class WebActivity extends AppCompatActivity {
    protected AgentWeb mAgentWeb;


    @BindView(R2.id.tool_title)
    TextView mToolTitle;
    @BindView(R2.id.article_detail_web_view)
    FrameLayout mArticleDetailWebView;
    @BindView(R2.id.tv_back)
    TextView mTvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("web_title");
        String url = getIntent().getStringExtra("web_url");
        mToolTitle.setText(title);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mArticleDetailWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);

    }


}
