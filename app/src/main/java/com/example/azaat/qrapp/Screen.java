package com.example.azaat.qrapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Screen extends Fragment implements  ZXingScannerView.ResultHandler{

    ZXingScannerView zXingScannerView;
    boolean inZ = true;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<Music> list;
    ListView listView;
    TextView textView;
    MyAdapter adapter;
    ScreenInterface screenInterface;
    Vibrator v;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen, container, false);
        list = new ArrayList<>();
        zXingScannerView = (ZXingScannerView) view.findViewById(R.id.zx);
        listView = (ListView)view.findViewById(R.id.list);
        textView = (TextView) view.findViewById(R.id.title) ;
        v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);


        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if( i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                    if(!inZ){
//                        zXingScannerView.setVisibility(View.VISIBLE);
//                        listView.setVisibility(View.GONE);
//                        textView.setVisibility(View.GONE);
//                    }
//
//                    return true;
//                }
//                return false;
//            }
//        });
        return view;
    }

    @Override
    public void handleResult(final Result result) {

//        screenInterface = new Retrofit.Builder().baseUrl("http://192.168.43.46/").addConverterFactory(GsonConverterFactory.create()).
//                build().create(ScreenInterface.class);
//        Call<List<Music>> call = screenInterface.getData(result.toString());
//        call.enqueue(new Callback<List<Music>>() {
//            @Override
//            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
//                zXingScannerView.setVisibility(View.GONE);
//                listView.setVisibility(View.VISIBLE);
//                textView.setVisibility(View.VISIBLE);
//                ArrayList<Music> list = (ArrayList<Music>) response.body();
//                textView.setText(list.get(0).getName());
//                adapter = new MyAdapter(getActivity(),list);
//                listView.setAdapter(adapter);
//                inZ = false;
//                //Toast.makeText(getActivity(), "yeah", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Music>> call, Throwable t) {
//                Toast.makeText(getActivity(), "No result"+result.toString(), Toast.LENGTH_SHORT).show();
//                zXingScannerView.resumeCameraPreview(Screen.this);
//                v.vibrate(100);
//            }
//        });
        Toast.makeText(getActivity(), ""+result, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zXingScannerView.stopCamera();
        if (!inZ)
            adapter.finish();

    }


}
