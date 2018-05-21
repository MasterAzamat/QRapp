package com.example.azaat.qrapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class BlankFragment extends Fragment {

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<Music> list;
    ListView listView;
    TextView textView;

    private String mess;

    MyAdapter adapter;
    public BlankFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);
        list = new ArrayList<>();
        listView = (ListView)view.findViewById(R.id.list);
        textView = (TextView) view.findViewById(R.id.title) ;
        mess = getArguments().getString("mess");
        dbHelper = new DBHelper(getActivity());
        db = dbHelper.getWritableDatabase();
        cursor = db.query("mytable",null,"song_id=?",new String[]{mess},null,null,null);
        if(cursor.moveToFirst()) {
            textView.setText(cursor.getString(cursor.getColumnIndex("name")));
            do {
                //list.add(new Music(cursor.getInt(cursor.getColumnIndex("song_loc")),cursor.getString(cursor.getColumnIndex("title")),cursor.getInt(cursor.getColumnIndex("song_id"))));
            } while (cursor.moveToNext());
            adapter = new MyAdapter(getActivity(), list);
            listView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.finish();
    }
}
