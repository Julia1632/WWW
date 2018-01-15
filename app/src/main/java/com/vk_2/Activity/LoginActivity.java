package com.vk_2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vk_2.Constants;
import com.vk_2.R;

/**
 * Created by User on 08.01.2018.
 */

public class LoginActivity extends Activity {
    WebView webView;
    private static final String TAG = "Kate.LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        webView=(WebView)findViewById(R.id.author_webview);
        // webView.loadUrl("https://oauth.vk.com/authorize?client_id=6272417&display=mobile&response_type=token");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);

        //Чтобы получать уведомления об окончании загрузки страницы
        webView.setWebViewClient(new VkontakteWebViewClient());

        //otherwise CookieManager will fall with java.lang.IllegalStateException: CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()

        String url= AuthorizationActivity.getUrl(Constants.API_ID, AuthorizationActivity.getSettings());
        webView.loadUrl(url);

    }

    class VkontakteWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            parseUrl(url);
        }
    }

    private void parseUrl(String url) {
        try {
            if(url==null)
                return;
            Log.i(TAG, "url="+url);
            if(url.startsWith(AuthorizationActivity.redirect_url))
            {
                if(!url.contains("error=")){
                    String[] auth= AuthorizationActivity.parseRedirectUrl(url);
                    Intent intent=new Intent();
                    intent.putExtra("token", auth[0]);
                    intent.putExtra("user_id", Long.parseLong(auth[1]));
                    setResult(Activity.RESULT_OK, intent);
                }
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

