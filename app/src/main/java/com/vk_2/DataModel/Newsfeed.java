package com.vk_2.DataModel;

import org.json.JSONObject;

/**
 * Created by User on 13.01.2018.
 */
////https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=V
//https://api.vk.com/method/newsfeed.get?count=10&access_token=
public class Newsfeed {
    int sourse_id;
    String photo_sourse;
    int date;
    String photo;
    String text;
    int comments;
    int likes;
    int reposts;
    int views;
    public Newsfeed parse(JSONObject jsonObject)  throws Exception{
        Newsfeed newsfeed=new Newsfeed();
        newsfeed.sourse_id = jsonObject.getInt("sourse_id");
        return newsfeed;
    }

    public int getSourse_id() {
        return sourse_id;
    }
}
