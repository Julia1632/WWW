package com.vk_2.DataModel;

import android.util.Log;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 13.01.2018.
 */
////https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=V
//https://api.vk.com/method/newsfeed.get?count=10&access_token=
public class Newsfeed {
    long source_id;
    String title;
    String photo_sourse;
    long date;
    String photo;



    public Newsfeed parse(JSONObject jsonObject) throws Exception {
        Newsfeed newsfeed = new Newsfeed();
        Log.e(TAG, "parse: " + jsonObject.getLong("source_id"));
        newsfeed.source_id = jsonObject.getLong("source_id");
        Log.e(TAG, "parse: news"+newsfeed.source_id );
        newsfeed.date = jsonObject.getLong("date");
        newsfeed.photo=jsonObject.getJSONArray("photos").getJSONObject(1).getString("src_max");


        return newsfeed;
    }

    public void add(long s,long d,String p){
        this.source_id=s;
        this.date=d;
        this.photo=p;
    }

    public long getSourse_id() {
        return source_id;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto_sourse(String photo_sourse) {
        this.photo_sourse = photo_sourse;
    }

    public long getSource_id() {
        return source_id;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoto_sourse() {
        return photo_sourse;
    }

    public long getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }



}
