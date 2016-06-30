package me.ewriter.dc4android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.ewriter.dc4android.apis.ApiManager;
import me.ewriter.dc4android.app.MyConstants;

public class AuthActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        if (mWebview != null) {
            mWebview.setDrawingCacheEnabled(true);
            mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            initWebViewSetting();
            mWebview.setWebViewClient(new AppWebViewClient());
        }

        String url = ApiManager.API_BASE_URL + "oauth/authorize"
                + "?client_id=" + MyConstants.CLIENT_ID +
                "&response_type=code" + "&redirect_uri=" + MyConstants.REDIRECT_URI;

        mWebview.loadUrl(url);
    }

    private void initWebViewSetting() {
        WebSettings webSettings = mWebview.getSettings();
        if (webSettings != null) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            //禁用表单的auto suggestion
            webSettings.setSaveFormData(false);
            webSettings.setSavePassword(false);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSupportZoom(false);
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk >= android.os.Build.VERSION_CODES.FROYO) {
                webSettings.setPluginState(WebSettings.PluginState.ON);
            }

            // 是否允许页面执行js脚本，注意不能去掉
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(false);
            webSettings.setAllowFileAccess(false);
            //start 第一次跳转穿越显示引导界面，以后均不显示。页面采用html5 localStorage存储
            webSettings.setDatabaseEnabled(true);
            String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            webSettings.setDatabasePath(dir);
            webSettings.setDomStorageEnabled(true);
        }
    }

    private class AppWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.startsWith("your:")) {
                handleRedirectUrl(url);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private void handleRedirectUrl(String url) {
        mWebview.stopLoading();
        Intent intent = new Intent();
        intent.putExtra("code_url", url);
        setResult(RESULT_OK, intent);
        finish();
    }
}
