package com.xdf.learn.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.SupperActivity;

/**
 * Created by xdf on 16-8-19.
 */
@ContentView(R.layout.act_web_layout)
public class WebViewTestActivity extends SupperActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, WebViewTestActivity.class);
    }

    @InjectView(R.id.id_act_web_view)
    com.tencent.smtt.sdk.WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.setWebViewClient(new MyWebViewClient());
//        mWebView.setDownloadListener(this);
        //支持多窗口模式
        mWebView.getSettings().setSupportMultipleWindows(true);
//        mWebView.loadUrl("http://www.sxyj.net/StaticPages/wxschema.html");
        mWebView.loadUrl("http://d.ireadercity.com/WebResource/page/spa/moonCake.html");
//        mWebView.loadUrl("http://engine.zuoyouxi.com/demo/misc/performance/StartGame.html");
    }

    private class MyWebViewClient extends com.tencent.smtt.sdk.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
