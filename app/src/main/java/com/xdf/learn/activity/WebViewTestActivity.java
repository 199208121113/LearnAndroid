package com.xdf.learn.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xdf.learn.R;
import com.xdf.learn.annotation.ContentView;
import com.xdf.learn.annotation.InjectView;
import com.xdf.learn.base.SupperActivity;

/**
 * Created by xdf on 16-8-19.
 */
@ContentView(R.layout.act_web_layout)
public class WebViewTestActivity extends SupperActivity {
    @InjectView(R.id.id_act_web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.setWebViewClient(new MyWebViewClient());
//        mWebView.setDownloadListener(this);
        //支持多窗口模式
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.loadUrl("http://www.sxyj.net/StaticPages/wxschema.html");
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
