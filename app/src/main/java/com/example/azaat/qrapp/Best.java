package com.example.azaat.qrapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Best extends Fragment {

    MediaPlayer mediaPlayer;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    ArrayList<Music> list;
    ListView listView;
    TextView textView;
    MyAdapter adapter;
    boolean inZ = true;

    AdapterForDelete adapterForDelete;
    boolean prep = false;
    Menu mainMenu;
    private String mess;

    public Best() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_best, container, false);
        list = new ArrayList<>();
        listView = (ListView)view.findViewById(R.id.list);
        textView = (TextView) view.findViewById(R.id.title) ;
        handler = new Handler();
        dbHelper = new DBHelper(getActivity());
        db = dbHelper.getWritableDatabase();
        cursor = db.query("best_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            inZ = false;
            do {
                list.add(new Music(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("song_loc"))));
            } while (cursor.moveToNext());
            adapter = new MyAdapter(getActivity(), list);
            listView.setAdapter(adapter);
        }
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "long", Toast.LENGTH_SHORT).show();
//                List<String> list = new ArrayList<>();
//                cursor = db.query("best_table",null,null,null,null,null,null);
//                if(cursor.moveToFirst()) {
//                    inZ = false;
//                    do {
//                        list.add(cursor.getString(cursor.getColumnIndex("title")));
//                    } while (cursor.moveToNext());
//                }
//                adapterForDelete = new AdapterForDelete(getActivity(),list);
//                adapterForDelete.setCheck(i);
//                listView.setAdapter(adapterForDelete);
//                mainMenu.setGroupVisible(R.id.delete_group,prep);
                return true;
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!inZ)
            adapter.finish();

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        mainMenu = menu;
        menu.setGroupVisible(R.id.delete_group,prep);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_for_delete,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_garbich:

                return false;
            case R.id.delete_all:

                return true;

        }
        return false;
    }
}
