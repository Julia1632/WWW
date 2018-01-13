package com.vk_2.DataModel;

import com.vk_2.Constants;

import org.json.JSONObject;

import java.io.Serializable;


public class AccountInfo implements Serializable {
    public String first_name;
    String last_name;
    String maiden_name;
    String screen_name;
    int sex;
    int relation;
    String bdate;
    String home_town;
    String status;
    String phone;
    public String url;


    //https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=V

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
        String screen_name=pAccountInfo.getString("screen_name");
        if (screen_name != null) {
            accountInfo.screen_name = screen_name;
        } else accountInfo.screen_name= Constants.EMPTYSTRING;
        accountInfo.bdate=pAccountInfo.getString("bdate");
        //if (sex == 1) {accountInfo.sex = "женский";
        //} else if (sex == 2) accountInfo.sex = "мужской";
        //else accountInfo.sex = "пол не указан";
        String home_town=pAccountInfo.getString("home_town");
        if (home_town != null) {
            accountInfo.home_town = home_town;
        } else accountInfo.home_town= Constants.EMPTYSTRING;
        String status=pAccountInfo.getString("status");
        if (status != null) {
            accountInfo.status = status;
        } else accountInfo.status= Constants.EMPTYSTRING;

        String phone=pAccountInfo.getString("phone");
        if (phone != null) {
            accountInfo.phone = phone;
        } else accountInfo.phone= Constants.EMPTYSTRING;

        accountInfo.url = pPhoto.getString("photo_max");

        return accountInfo;
    }


}
