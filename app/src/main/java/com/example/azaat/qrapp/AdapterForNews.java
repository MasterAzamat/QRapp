package com.example.azaat.qrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Azaat on 04.04.2018.
 */

public class AdapterForNews extends BaseAdapter {
    Context context;
    List<articles> list;
    LayoutInflater inflater ;

    ImageView img;
    TextView title,author;

    public AdapterForNews(Context context, List<articles> list) {
        this.context = context;
        this.list = list;
        inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.news_item,viewGroup,false);
        }
        img = (ImageView) view.findViewById(R.id.news_image);
        title = (TextView) view.findViewById(R.id.news_title);
        author = (TextView) view.findViewById(R.id.news_author);

        title.setText(list.get(i).getTitle());
        author.setText(list.get(i).getAuthor());
        Glide.with(context).load(list.get(i).getImg()).into(img);
        return view;
    }
}
