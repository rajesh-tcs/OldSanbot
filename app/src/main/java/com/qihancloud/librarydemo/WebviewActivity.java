package com.qihancloud.librarydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qihancloud.librarydemo.appconstant.AppConstant;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {

    @Bind(R.id.wv_botframe)
    WebView wvBotFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        wvBotFrame.setWebViewClient(new MyBrowser());
        wvBotFrame.getSettings().setLoadsImagesAutomatically(true);
        wvBotFrame.getSettings().setJavaScriptEnabled(true);
        wvBotFrame.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvBotFrame.loadUrl(AppConstant.BOTFRAME_URL);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
