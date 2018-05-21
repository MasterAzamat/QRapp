package com.example.azaat.qrapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.w3c.dom.UserDataHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class News extends Fragment {

    NewsInterface newsInterface;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_news, container, false);
//        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse("http://192.168.43.46/songs/song_1.mp4"));
//        mediaPlayer.start();

        list = (ListView) view.findViewById(R.id.news_list);
        newsInterface = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).
                build().create(NewsInterface.class);
        Call<NewsClass> call = newsInterface.classForUse();
        call.enqueue(new Callback<NewsClass>() {
            @Override
            public void onResponse(Call<NewsClass> call, Response<NewsClass> response) {
                NewsClass newsClass = response.body();
                ArrayList<articles> articles = newsClass.getArticles();
                AdapterForNews adapterForNews = new AdapterForNews(getActivity(),articles);
                list.setAdapter(adapterForNews);

             }

            @Override
            public void onFailure(Call<NewsClass> call, Throwable t) {

            }
        });
        return view;
    }



}
