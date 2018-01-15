package com.vk_2.DataModel;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 13.01.2018.
 */

public class ArrayNewsfeed extends ArrayList<Newsfeed> {


    // реализовать парсинг
    public ArrayNewsfeed parse(JSONArray jsonArray) throws Exception {

        Log.e(TAG, "parse: json" );
        ArrayNewsfeed arrayNewfeed = new ArrayNewsfeed();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject onenew = jsonArray.getJSONObject(i);
            try {
                arrayNewfeed.add(new Newsfeed().parse(onenew));
            } catch (Exception e) {
            }

        }
        return arrayNewfeed;

    }

}
