package com.vk_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Account {
    public String access_token;
    public long user_id;

    public void save(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        editor.putString(Constants.TOKEN, access_token);
        editor.putLong(Constants.USER_ID, user_id);
        editor.commit();
    }

    public void restore(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        access_token = prefs.getString(Constants.TOKEN, "");
        user_id = prefs.getLong(String.valueOf(Constants.USER_ID), 0);
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


}
