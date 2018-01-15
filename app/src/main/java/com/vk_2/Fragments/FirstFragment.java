package com.vk_2.Fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk_2.Constants;
import com.vk_2.DB.AccountInfoDb;
import com.vk_2.DB.SqlConnector;
import com.vk_2.DataModel.AccountInfo;
import com.vk_2.ImageLoader.Malevich;
import com.vk_2.Json.JsonRes;
import com.vk_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

;


/**
 * Created by User on 10.01.2018.
 */

public class FirstFragment extends Fragment {
    String TAG = "FirstFragment";


    public SqlConnector mSqlConnector;
    String url = "https://api.vk.com/method/account.getProfileInfo?v=5.69&access_token=";
    String ulrGetPhoto = "https://api.vk.com/method/users.get?fields=photo_max&access_token=";


    public static FirstFragment newInstance(String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("Title", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate:  first f");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        TextView firstNameTextView = view.findViewById(R.id.first_name_textview);
        TextView screenNameTextView = view.findViewById(R.id.screen_name_textview);
        TextView sexTextView = view.findViewById(R.id.sex_textview);
        TextView relationTextView = view.findViewById(R.id.relation_textview);
        TextView bdateTextView = view.findViewById(R.id.bdate_textview);
        TextView homeTownTextView = view.findViewById(R.id.home_town_textview);
        TextView statusTextView = view.findViewById(R.id.status_textview);
        TextView phoneTextView = view.findViewById(R.id.phone_textview);
        ImageView imageView = view.findViewById(R.id.account_image_view);
        String first_name = "";
        String last_name = "";
        String maiden_name = "";
        String screen_name = "";
        String sex = "";
        String relation = "";
        String bdate = "";
        String homeTown = "";
        String status = "";
        String phone = "";
        String profilePhoto = "";

        mSqlConnector = new SqlConnector(getContext());
        SQLiteDatabase readableConnection = mSqlConnector.getReadableDatabase();
        Cursor cursor = readableConnection.query(AccountInfoDb.TABLE, new String[]{AccountInfoDb.ID}, null, null, AccountInfoDb.ID, Constants.USER_ID_VALUE, null);


        if (cursor.getCount() == 0) {
            try {
                cursor.close();
                String s = new JsonRes().res(url, Constants.TOKEN_VALUE);
                String sp = new JsonRes().res(ulrGetPhoto, Constants.TOKEN_VALUE);
                Log.e(TAG, "onCreateView: " + s);
                JSONObject t = new JSONObject(s);
                JSONObject response = t.optJSONObject("response");
                JSONObject p = new JSONObject(sp);
                JSONArray ph = p.optJSONArray("response");
                JSONObject photo = ph.getJSONObject(0);
                AccountInfo a = new AccountInfo().parse(response, photo);
                Log.e(TAG, "onCreateView: " + a.getUrl());
                SQLiteDatabase writeConnection = mSqlConnector.getWritableDatabase();
                writeConnection.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put(AccountInfoDb.ID, Constants.USER_ID_VALUE);
                contentValues.put(AccountInfoDb.FIRST_NAME, a.getFirst_name());
                contentValues.put(AccountInfoDb.LAST_NAME, a.getLast_name());
                contentValues.put(AccountInfoDb.MAIDEN_NAME, a.getMaiden_name());
                contentValues.put(AccountInfoDb.SCREEN_NAME, a.getScreen_name());
                contentValues.put(AccountInfoDb.SEX, a.getSex());
                contentValues.put(AccountInfoDb.RELATION, a.getRelation());
                contentValues.put(AccountInfoDb.BDATE, a.getBdate());
                contentValues.put(AccountInfoDb.HOME_TOWN, a.getHome_town());
                contentValues.put(AccountInfoDb.STATUS, a.getStatus());
                contentValues.put(AccountInfoDb.PHONE, a.getPhone());
                contentValues.put(AccountInfoDb.URL, a.getUrl());
                writeConnection.insert(AccountInfoDb.TABLE, null, contentValues);
                writeConnection.setTransactionSuccessful();
                writeConnection.endTransaction();
                Log.e(TAG, "onCreateView: emtpy");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else Log.e(TAG, "onCreateView: full");

        SQLiteDatabase readableConnection2 = mSqlConnector.getReadableDatabase();
        Cursor cursor2 = readableConnection.query(AccountInfoDb.TABLE, new String[]{AccountInfoDb.ID, AccountInfoDb.FIRST_NAME,
                AccountInfoDb.LAST_NAME, AccountInfoDb.MAIDEN_NAME, AccountInfoDb.SCREEN_NAME, AccountInfoDb.SEX,
                AccountInfoDb.RELATION, AccountInfoDb.BDATE, AccountInfoDb.HOME_TOWN, AccountInfoDb.STATUS, AccountInfoDb.PHONE, AccountInfoDb.URL
        }, null, null, AccountInfoDb.ID, Constants.USER_ID_VALUE, null);
        while (cursor2.moveToNext()) {
            String id = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.ID));
            Log.e(TAG, "onCreate: " + id);
            first_name = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.FIRST_NAME));
            Log.e(TAG, "onCreate: fn " + first_name);
            last_name = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.LAST_NAME));
            Log.e(TAG, "onCreate: lf " + cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.LAST_NAME)));
            maiden_name = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.MAIDEN_NAME));
            screen_name = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.SCREEN_NAME));
            sex = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.SEX));
            relation = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.RELATION));
            bdate = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.BDATE));
            homeTown = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.HOME_TOWN));
            status = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.STATUS));
            phone = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.PHONE));
            profilePhoto = cursor2.getString(cursor2.getColumnIndex(AccountInfoDb.URL));


        }
        cursor2.close();
        AccountInfo accountInfo = new AccountInfo(first_name, last_name, maiden_name, screen_name, Integer.valueOf(sex), Integer.valueOf(relation), bdate, homeTown, status, phone, profilePhoto);
        firstNameTextView.setText(accountInfo.getFirst_name() + " " + accountInfo.getLast_name() + " " + accountInfo.getMaiden_name());
        screenNameTextView.setText(accountInfo.getScreen_name());
        if (accountInfo.getSex() == 1)
            sex = "Женский";
        else if (accountInfo.getSex() == 2) sex = "Mужской";
        else sex = "Не указано";
        sexTextView.setText(sex);
        relationTextView.setText(String.valueOf(accountInfo.getRelation()));
        bdateTextView.setText(accountInfo.getBdate());
        homeTownTextView.setText(accountInfo.getHome_town());
        statusTextView.setText(accountInfo.getStatus());
        phoneTextView.setText(accountInfo.getPhone());

        Malevich.INSTANCE.load(profilePhoto).into(imageView);


       /*


        accountInfo=new AccountInfo("1fn","2ln");
        mSqlConnector=new SqlConnector(getContext());

        SQLiteDatabase writeConnection=mSqlConnector.getWritableDatabase();
        writeConnection.beginTransaction();
        ContentValues contentValues=new ContentValues();
        contentValues.put(AccountInfoDb.ID,"1");
        contentValues.put(AccountInfoDb.FIRST_NAME,accountInfo.getFirst_name());
        contentValues.put(AccountInfoDb.LAST_NAME,accountInfo.getLast_name());
       // writeConnection.insert(AccountInfoDb.TABLE,null,contentValues);
         writeConnection.delete(AccountInfoDb.TABLE,null,null);
        writeConnection.setTransactionSuccessful();
        writeConnection.endTransaction();

        SQLiteDatabase readableConnection=mSqlConnector.getReadableDatabase();
        Cursor cursor=readableConnection.query(AccountInfoDb.TABLE,new String[]{AccountInfoDb.ID,AccountInfoDb.FIRST_NAME,AccountInfoDb.LAST_NAME},null,null,AccountInfoDb.ID,null,null);

        if (cursor.getCount()==0){
            Log.e(TAG, "onCreate: empty" );
        }else Log.e(TAG, "onCreate: full" );

        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(AccountInfoDb.ID));
            Log.e(TAG, "onCreate: "+id);
            String fn=cursor.getString(cursor.getColumnIndex(AccountInfoDb.FIRST_NAME));
            Log.e(TAG, "onCreate: fn "+fn);
            Log.e(TAG, "onCreate: lf "+cursor.getString(cursor.getColumnIndex(AccountInfoDb.LAST_NAME)));
        }
        cursor.close();*/


        return view;
    }
}
