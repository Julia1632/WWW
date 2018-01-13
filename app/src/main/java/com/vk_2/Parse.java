package com.vk_2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 09.01.2018.
 */

public class Parse extends AsyncTask<String,Void,String> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    public static String LOG_TAG = "my_log";
    String token;

    public Parse(String ptoken) {
        this.token = ptoken;
    }

    @Override
    protected String doInBackground(String... param) {
        String ur=param[0];
        try {
            URL url = new URL(ur+token);
                    //"http://androiddocs.ru/api/friends.json");
           // Log.e(TAG, "doInBackground: "+token);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
           // Log.e(TAG, "doInBackground: after connect" );


            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
           // Log.e(TAG, "doInBackground: create string" );
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();
           // Log.e(TAG, "doInBackground: to result "+resultJson );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

}
