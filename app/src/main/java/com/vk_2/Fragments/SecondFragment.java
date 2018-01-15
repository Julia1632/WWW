package com.vk_2.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vk_2.DataModel.Item;
import com.vk_2.R;
import com.vk_2.RecycleAdapter.MyAdapter;
import com.vk_2.RecycleAdapter.ILoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 10.01.2018.
 */

public class SecondFragment extends Fragment {

    private String title;
    String[] urls = {
            "http://r.ddmcdn.com/s_f/o_1/cx_0/cy_0/cw_300/ch_300/w_300/APL/uploads/2014/10/kitten-cuteness300.jpg",
            "https://cmeimg-a.akamaihd.net/640/clsd/getty/c64f76dc20c246ca88ee180fe4b4b781",
            "http://r.ddmcdn.com/s_f/o_1/cx_462/cy_245/cw_1349/ch_1349/w_720/APL/uploads/2015/06/caturday-shutterstock_149320799.jpg",
            "https://vetstreet.brightspotcdn.com/dims4/default/a1a90c7/2147483647/thumbnail/180x180/quality/90/?url=https%3A%2F%2Fvetstreet-brightspot.s3.amazonaws.com%2F0d%2Ff2e4c0b3a611e092fe0050568d634f%2Ffile%2Fhub-cats-senior.jpg",
            "https://media.boingboing.net/wp-content/uploads/2017/03/surprised-cat-04.jpg",
            "http://cdn1-www.cattime.com/assets/uploads/gallery/persian-cats-and-kittens/persian-cats-and-kittens-1.jpg", "http://r.ddmcdn.com/s_f/o_1/cx_0/cy_0/cw_300/ch_300/w_300/APL/uploads/2014/10/kitten-cuteness300.jpg",
            "https://cmeimg-a.akamaihd.net/640/clsd/getty/c64f76dc20c246ca88ee180fe4b4b781",
            "http://r.ddmcdn.com/s_f/o_1/cx_462/cy_245/cw_1349/ch_1349/w_720/APL/uploads/2015/06/caturday-shutterstock_149320799.jpg",
            "https://vetstreet.brightspotcdn.com/dims4/default/a1a90c7/2147483647/thumbnail/180x180/quality/90/?url=https%3A%2F%2Fvetstreet-brightspot.s3.amazonaws.com%2F0d%2Ff2e4c0b3a611e092fe0050568d634f%2Ffile%2Fhub-cats-senior.jpg",
            "https://media.boingboing.net/wp-content/uploads/2017/03/surprised-cat-04.jpg"};

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    public static SecondFragment newInstance(String title) {
        SecondFragment fragmentFirst = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        TextView tvLabel = view.findViewById(R.id.first_name_textview);

        random10Data(0, 10);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Log.e(TAG, "onCreateView: recycle");

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MyAdapter(recyclerView, view.getContext(), items);
        recyclerView.setAdapter(adapter);


        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    items.add(null);
                    adapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            adapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index + 10;
                            random10Data(index, end);
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(view.getContext(), "Load data completed !!!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;
    }

    private void random10Data(int indexStart, int indexEnd) {

        for (int i = indexStart; i < indexEnd; i++) {
            String name = UUID.randomUUID().toString();
            Item newItem = new Item(name, name.length(), geturls(i));
            items.add(newItem);
        }
    }

    private String geturls(int index) {

        return urls[index % 10];
    }
}
