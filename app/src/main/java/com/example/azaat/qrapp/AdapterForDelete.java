package com.example.azaat.qrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Azaat on 14.04.2018.
 */

public class AdapterForDelete extends BaseAdapter {

    Context context;
    List<String> list;
    boolean arr[];
    CheckBox checkBoxList[];

    public AdapterForDelete(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        arr = new boolean[list.size()];
        checkBoxList = new CheckBox[list.size()];
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_for_delete,viewGroup,false);
            viewHolder.title = (TextView) view.findViewById(R.id.item_title);
            viewHolder.box = (CheckBox) view.findViewById(R.id.delete_check);
            checkBoxList[i] = viewHolder.box;
            viewHolder.title.setText(list.get(i));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(viewHolder.box.isChecked()){
                        viewHolder.box.setChecked(false);
                        arr[i] = false;
                    }
                    else{
                        viewHolder.box.setChecked(true);
                        arr[i] = true;
                    }
                }
            });
        }else {
            viewHolder =(AdapterForDelete.ViewHolder) view.getTag();
        }
        return view;
    }
    private class ViewHolder{
        TextView title;
        CheckBox box ;
    }
    public  void deleteItem(){
        String d = "";
        for (int i = 0;i<arr.length;i++){
            if (arr[i]) d +=i;
        }
        Toast.makeText(context, d, Toast.LENGTH_SHORT).show();
    }
    public  void setCheck(int pos){
        checkBoxList[pos].setChecked(true);
    }
}
