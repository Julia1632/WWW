package com.vk_2.DataModel;

import com.vk_2.Constants;

import org.json.JSONObject;

import java.io.Serializable;


public class AccountInfo implements Serializable {
    String first_name;
    String last_name;
    String maiden_name;
    String screen_name;
    int sex;
    int relation;
    String bdate;
    String home_town;
    String status;
    String phone;
    String url;


    //https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=V


    public AccountInfo() {
    }

    public AccountInfo parse(JSONObject pAccountInfo, JSONObject pPhoto) throws Exception {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.first_name = pAccountInfo.getString("first_name");
        accountInfo.last_name = pAccountInfo.getString("last_name");
        String maiden_name = pAccountInfo.getString("maiden_name");
        if (maiden_name != null) {
            accountInfo.maiden_name = maiden_name;
        }
        accountInfo.sex = pAccountInfo.getInt("sex");
        accountInfo.relation=pAccountInfo.getInt("relation");
        String screen_name=pAccountInfo.optString("screen_name");
        if (screen_name != null) {
            accountInfo.screen_name = screen_name;
        } else accountInfo.screen_name= Constants.EMPTY_STRING;
        accountInfo.bdate=pAccountInfo.getString("bdate");
        //if (sex == 1) {accountInfo.sex = "женский";
        //} else if (sex == 2) accountInfo.sex = "мужской";
        //else accountInfo.sex = "пол не указан";
        String home_town=pAccountInfo.getString("home_town");
        if (home_town != null) {
            accountInfo.home_town = home_town;
        } else accountInfo.home_town= Constants.EMPTY_STRING;
        String status=pAccountInfo.getString("status");
        if (status != null) {
            accountInfo.status = status;
        } else accountInfo.status= Constants.EMPTY_STRING;

        String phone=pAccountInfo.getString("phone");
        if (phone != null) {
            accountInfo.phone = phone;
        } else accountInfo.phone= Constants.EMPTY_STRING;

        accountInfo.url = pPhoto.getString("photo_max");

        return accountInfo;
    }



    public AccountInfo(String first_name, String last_name, String maiden_name, String screen_name, int sex, int relation, String bdate, String home_town, String status, String phone, String url) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.maiden_name = maiden_name;
        this.screen_name = screen_name;
        this.sex = sex;
        this.relation = relation;
        this.bdate = bdate;
        this.home_town = home_town;
        this.status = status;
        this.phone = phone;
        this.url = url;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMaiden_name() {
        return maiden_name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public int getSex() {
        return sex;
    }

    public int getRelation() {
        return relation;
    }

    public String getBdate() {
        return bdate;
    }

    public String getHome_town() {
        return home_town;
    }

    public String getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }
}
