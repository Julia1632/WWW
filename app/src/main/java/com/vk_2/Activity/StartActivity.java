package com.vk_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vk_2.Account;
import com.vk_2.DataModel.AccountInfo;
import com.vk_2.Json.JsonRes;
import com.vk_2.R;

/**
 * Created by User on 15.01.2018.
 */

public class StartActivity extends AppCompatActivity {
    int REQESTCODE = 1;
    private static final String TAG = "Main";
    Button buttonlogout;
    Account account = new Account();

    String url = "https://api.vk.com/method/account.getProfileInfo?v=5.69&access_token=";
    String ulrGetPhoto = "https://api.vk.com/method/users.get?fields=photo_max&access_token=";
    AccountInfo a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();
        account.restore(this);
        Log.e(TAG, "onCreate: " + account.access_token);
        if (account.access_token != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("1", account.access_token);
            intent.putExtra("2", String.valueOf(account.user_id));
            startActivity(intent);
        } else
            startLoginActivity();
        Log.e(TAG, "onCreate: " + account.access_token);


    }

    public void initviews() {
        buttonlogout = (Button) findViewById(R.id.log_out_button);
        buttonlogout.setOnClickListener(logout);
    }

    public View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s = new JsonRes().res(url, account.access_token);
            String sp = new JsonRes().res(ulrGetPhoto, account.access_token);
            Log.e(TAG, "onClick: gg" + s);
            Log.e(TAG, "onClick: jj" + sp);


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
            Log.e(TAG, "onActivityResult: " + account.access_token);
            account.user_id = data.getLongExtra("user_id", 0);
            account.save(StartActivity.this);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("1", account.access_token);
            intent.putExtra("2", String.valueOf(account.user_id));
            startActivity(intent);


        }
    }


}

