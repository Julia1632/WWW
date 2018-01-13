package com.vk_2.DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 13.01.2018.
 */

public class ArrayNewsfeed extends ArrayList<Newsfeed> {


    // реализовать парсинг
    public ArrayNewsfeed parse(JSONArray jsonArray) throws Exception {
        JSONObject onenew;
        ArrayNewsfeed arrayNewfeed = new ArrayNewsfeed();
        for (int i = 0; i < jsonArray.length(); i++) {
            onenew = jsonArray.getJSONObject(i);
            try {
                arrayNewfeed.add(new Newsfeed().parse(onenew));
            } catch (Exception e) {
            }

        }
        return arrayNewfeed;

    }

}
