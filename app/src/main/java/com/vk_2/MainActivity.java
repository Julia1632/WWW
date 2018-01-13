package com.vk_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vk_2.DataModel.AccountInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    int REQESTCODE = 1;
    private static final String TAG = "Main";
    Button buttonlogout;
    Account account = new Account();
    Api api;
    String url="https://api.vk.com/method/account.getProfileInfo?v=5.69&access_token=";
    String ulrGetPhoto="https://api.vk.com/method/users.get?fields=photo_max&access_token=";
    AccountInfo a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();
        //account.restore(this);
        Log.e(TAG, "onCreate: "+account.access_token);
        if (account.access_token!=null){}
        else
        startLoginActivity();
        Log.e(TAG, "onCreate: "+account.access_token);



    }

    public void initviews() {
        buttonlogout = (Button) findViewById(R.id.log_out_button);
        buttonlogout.setOnClickListener(logout);
    }

    public View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s=new JsonRes().res(url,account.access_token);
            String sp=new JsonRes().res(ulrGetPhoto,account.access_token);
            Log.e(TAG, "onClick: gg"+s);
            Log.e(TAG, "onClick: jj"+sp);
            try {
                JSONObject t=new JSONObject(s);
                JSONObject response = t.optJSONObject("response");
                JSONObject p=new JSONObject(sp);
                JSONArray ph=p.optJSONArray("response");
                JSONObject photo=ph.getJSONObject(0);
                a=new AccountInfo().parse(response,photo);
                Log.e(TAG, "onClick: "+a.first_name);
                Log.e(TAG, "onClick: "+a.url );
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(TAG, "after parse"+a.first_name );


        }
    };

    private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, REQESTCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQESTCODE) && (resultCode == RESULT_OK)) {
            //Authorized successfully
            account.access_token = data.getStringExtra("token");
            Log.e(TAG, "onActivityResult: "+account.access_token);
            account.user_id = data.getLongExtra("user_id",0);
            account.save(MainActivity.this);
            api = new Api(account.access_token, Constants.API_ID);
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("1",account.access_token);
            intent.putExtra("2",String.valueOf(account.user_id));
            startActivity(intent);


        }
    }


}

