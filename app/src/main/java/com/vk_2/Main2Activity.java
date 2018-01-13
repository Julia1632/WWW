package com.vk_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.vk_2.DataModel.AccountInfo;
import com.vk_2.DataModel.ArrayNewsfeed;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2";
    String url = "https://api.vk.com/method/account.getProfileInfo?v=5.69&access_token=";
    String ulrGetPhoto = "https://api.vk.com/method/users.get?fields=photo_max&access_token=";
    String urlnews = "https://api.vk.com/method/newsfeed.get?count=2&access_token=";
    Account account;
    TextView textView;
    AccountInfo a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String st = intent.getStringExtra("1");
        Log.e(TAG, "onCreate: main 2 " + st);
        String st2 = intent.getStringExtra("2");
        Log.e(TAG, "onCreate: main 2 " + st2);
       // String s = new JsonRes().res(url, st);
       // String sp = new JsonRes().res(ulrGetPhoto, st);
        String sn = new JsonRes().res(urlnews, st);
       // Log.e(TAG, "onClick: gg" + s);
       // Log.e(TAG, "onClick: jj" + sp);
       // Log.e(TAG, "onCreate: news " + sn);
        try {
           /* JSONObject t = new JSONObject(s);
            JSONObject response = t.optJSONObject("response");

            JSONObject p = new JSONObject(sp);
            JSONArray ph = p.optJSONArray("response");
            JSONObject photo = ph.getJSONObject(0);*/

            JSONObject news = new JSONObject(sn);
            JSONObject newso = news.optJSONObject("response");
            JSONArray arnews = newso.getJSONArray("items");
            JSONObject start=newso.optJSONObject("start_from");

           // AccountInfo a = new AccountInfo().parse(response, photo);
            ArrayNewsfeed array=new ArrayNewsfeed().parse(arnews);
           //ArrayNewsfeed an=new ArrayNewsfeed().parse(arnews);
           Log.e(TAG, "onCreate: news="+arnews.length() );
            Log.e(TAG, "onCreate: Hghghg" + array.size());
            textView = (TextView) findViewById(R.id.first_name_textview);
            textView.setText(a.first_name);
        } catch (Exception e) {
        }

      //  Log.e(TAG, "after parse" + s);

    }
}

