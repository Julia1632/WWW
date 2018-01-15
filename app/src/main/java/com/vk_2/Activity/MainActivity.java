package com.vk_2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vk_2.Constants;
import com.vk_2.DataModel.AccountInfo;
import com.vk_2.Fragments.FirstFragment;
import com.vk_2.Fragments.SecondFragment;
import com.vk_2.ImageLoader.Malevich;
import com.vk_2.R;

public class MainActivity extends AppCompatActivity {
    int REQESTCODE = 1;
    private static final String TAG = "Main2";
    String url = "https://api.vk.com/method/account.getProfileInfo?v=5.69&access_token=";
    String ulrGetPhoto = "https://api.vk.com/method/users.get?fields=photo_max&access_token=";
    String urlnews = "https://api.vk.com/method/newsfeed.get?count=1&filters=wall_photo&access_token=";
    String access_token = "&access_token=";
    String urlGetphotoUser = "https://api.vk.com/method/users.get?fields=photo_50&user_id=";

    String urlGetphotoGroup = "https://api.vk.com/method/groups.getById?fields=photo_50&group_ids=";

    TextView textView;
    AccountInfo a;
    FragmentPagerAdapter adapterViewPager;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String st = intent.getStringExtra("1");
        Log.e(TAG, "onCreate: main 2 " + st);
        Constants.TOKEN_VALUE =st;
        String user_id = intent.getStringExtra("2");
        Log.e(TAG, "onCreate: main 2 " + user_id);
        Constants.USER_ID_VALUE =user_id;
/*
        String sn = new JsonRes().res(urlnews, st);
        Log.e(TAG, "onCreate: "+sn);
        List<Newsfeed> arraynews = new ArrayList<>();
        Newsfeed newsfeed=new Newsfeed();
        try {
            JSONObject news = new JSONObject(sn);
            Log.e(TAG, "onCreate:sn "+sn );
            JSONObject newso = news.getJSONObject("response");
            Log.e(TAG, "onCreate: news="+news.toString() );
            JSONArray arnews = newso.getJSONArray("items");
            Log.e(TAG, "onCreate: arnews="+arnews.toString() );
            Log.e(TAG, "onCreate: "+arnews.getJSONObject(0));

            Log.e(TAG, "onCreate: "+arnews.isNull(1));
            Log.e(TAG, "parse: " + arnews.getJSONObject(0).getLong("source_id"));

            Log.e(TAG, "parse: news"+arnews.getJSONObject(0).getString("date"));



            Log.e(TAG, "onCreate:jj "+newso.getString("next_from"));

            newsfeed.add(arnews.getJSONObject(0).getLong("source_id"),arnews.getJSONObject(0).getLong("date"),arnews.getJSONObject(0).getJSONArray("photos").getJSONObject(1).getString("src_max"));
            Log.e(TAG, "onCreate: photo"+newsfeed.getPhoto());
                arraynews.add(newsfeed);


        }
        catch (Exception e) {
        }


        Log.e(TAG, "onCreate: "+arraynews.size());

//*/



        imageView = (ImageView) findViewById(R.id.id_image);
        imageView.setOnClickListener(clickimage);
        Malevich.INSTANCE.setConfig(new Malevich.Config(getCacheDir()));
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance("Профиль");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return SecondFragment.newInstance("Новости");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        public CharSequence getPageTitle(int position) {

            if(position==0)
            return "Профиль";
            else return "Новости";
        }

    }

    View.OnClickListener clickimage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showPopMenu(view);
        }
    };

    private void showPopMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popmenuitem);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1) {
                    Intent intent=getIntent();
                    intent.setClass(getBaseContext(), LoginActivity.class);
                    startActivityForResult(intent, REQESTCODE);
                    Toast.makeText(getApplicationContext(),
                            "Вы выбрали PopupMenu 1",
                            Toast.LENGTH_SHORT).show();
                    return true;
                } else return false;
            }
        });
        popupMenu.show();
    }

}


        /* Intent intent = getIntent();
        String st = intent.getStringExtra("1");
        Log.e(TAG, "onCreate: main 2 " + st);
        String user_id = intent.getStringExtra("2");
        Log.e(TAG, "onCreate: main 2 " + user_id);
        // String s = new JsonRes().res(url, st);
        // String sp = new JsonRes().res(ulrGetPhoto, st);
        String sn = new JsonRes().res(urlnews, st);
        // Log.e(TAG, "onClick: gg" + s);
        // Log.e(TAG, "onClick: jj" + sp);
        Log.e(TAG, "onCreate: news " + sn);
        try {
           /* JSONObject t = new JSONObject(s);
            JSONObject response = t.optJSONObject("response");

            JSONObject p = new JSONObject(sp);
            JSONArray ph = p.optJSONArray("response");
            JSONObject photo = ph.getJSONObject(0);*/
/*
            JSONObject news = new JSONObject(sn);
            JSONObject newso = news.optJSONObject("response");
            JSONArray arnews = newso.getJSONArray("items");
            Log.e(TAG, "onCreate: "+arnews.isNull(1));
            JSONObject start = newso.optJSONObject("start_from");

            // AccountInfo a = new AccountInfo().parse(response, photo);
            //ArrayNewsfeed array = new ArrayNewsfeed().parse(arnews);
            //addphoto(array, st);
        /*    JSONObject o1 = arnews.getJSONObject(0);
            int l = o1.getInt("source_id");
            List<Newsfeed> arraynews = new ArrayList<>();

            for (int i = 0; i < arnews.length(); i++) {

                //JSONObject onenew = arnews.getJSONObject(i);
               // Log.e(TAG, "onCreate: " + onenew.toString());
              //  Newsfeed news1 = new Newsfeed().parse(onenew);
            //    arraynews.add(news1);
               // Log.e(TAG, "parse: to" + news1.getSourse_id());
                //Log.e(TAG, "parse: " + i);
          //  }

            /*for (int i = 0; i < arraynews.size(); i++) {
            if (arraynews.get(i).getSourse_id() < 0) {
                    String result = new JsonRes().res(urlGetphotoGroup +arraynews.get(i).getSourse_id() + access_token, st);
                    JSONObject response = new JSONObject(result);
                    JSONObject group = response.getJSONObject("response");
                    //arraynews.get(i).setPhoto_sourse(group.getString("photo_50"));
                    //arraynews.get(1).setTitle(group.getString("screen_name"));
                }
            }*/


            //ArrayNewsfeed an = new ArrayNewsfeed().parse(arnews);
            // Log.e(TAG, "onCreate: news=" + arraynews.get(0).getSourse_id());
/*            Log.e(TAG, "onCreate: Hghghg" + arraynews.size());

            Log.e(TAG, "onCreate: source id" + arraynews.get(0).getSourse_id());
            Log.e(TAG, "onCreate: date"+arraynews.get(0).getDate() );
            Log.e(TAG, "onCreate: text"+arraynews.get(0).getText().length());
            Log.e(TAG, "onCreate: comments"+arraynews.get(0).getComments() );
            Log.e(TAG, "onCreate: likes"+arraynews.get(0).getLikes());
            Log.e(TAG, "onCreate: posts "+arraynews.get(0).getReposts() );
           // Log.e(TAG, "onCreate: src "+arraynews.get(0).getPhoto() );
            Log.e(TAG, "onCreate: source id" + arraynews.get(1).getSourse_id());
            textView = (TextView) findViewById(R.id.first_name_textview);
            //textView.setText(String.valueOf(ne.getSourse_id()));
        } catch (Exception e) {
        }

        //  Log.e(TAG, "after parse" + s);

    }

  /*  private void addphoto(ArrayNewsfeed array, String st) throws JSONException {

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getSourse_id() > 0) {

                String result = new JsonRes().res(urlGetphotoUser + array.get(i).getSourse_id()+ access_token, st);
                JSONObject response = new JSONObject(result);
                JSONObject user = response.getJSONObject("response");
                array.get(i).setPhoto_sourse(user.getString("photo_max"));
                array.get(1).setTitle(user.getString("first_name") + user.getString("last_name"));
            } else if (array.get(i).getSourse_id() < 0) {
                String result = new JsonRes().res(urlGetphotoGroup +array.get(i).getSourse_id() + access_token, st);
                JSONObject response = new JSONObject(result);
                JSONObject group = response.getJSONObject("response");
                array.get(i).setPhoto_sourse(group.getString("photo_50"));
                array.get(1).setTitle(group.getString("screen_name"));
            }
        }
    }*/

   /* public List<Newsfeed> parse(JSONArray jsonArray) throws JSONException {
       // Log.e(TAG, "parse: json");
        List<Newsfeed> arraynews = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject onenew = jsonArray.getJSONObject(i);
            try {
                Newsfeed news=new Newsfeed().parse(onenew);
               // arraynews.add(news);
                Log.e(TAG, "parse: to" + news.getSourse_id());
                Log.e(TAG, "parse: "+i );
            } catch (Exception e) {
            }


        }
        return arraynews;

}*/

