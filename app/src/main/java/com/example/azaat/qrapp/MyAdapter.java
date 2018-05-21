package com.example.azaat.qrapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azaat on 28.03.2018.
 */

public class MyAdapter extends BaseAdapter{
    int a = 0;

    Context context;
    List<Music> list;
    ArrayList<ImageView> item_play = new ArrayList<>();
    ArrayList<SeekBar> item_seek = new ArrayList<>();
    ArrayList<MediaPlayer> item_media = new ArrayList<>();
    int layout;
    boolean flag = false;
    MediaPlayer mediaPlay;
    Runnable runnable;
    Handler handler;
    int play_pos = -1;

    DBHelper dbHelper;
    SQLiteDatabase db;

    public MyAdapter(Context context, List<Music> list) {
        this.context = context;
        this.list = list;
        layout = R.layout.item;
        handler = new Handler();
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
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

    private  class ViewHolder{
        ImageView play,menu;
        SeekBar seek;
        TextView title;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Music music = list.get(i);
        a += (i+1);
        //Toast.makeText(context, ""+a, Toast.LENGTH_SHORT).show();
        final int pos = i;
        final ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item,viewGroup,false);
            viewHolder.play = (ImageView) view.findViewById(R.id.play);
            viewHolder.play.setTag(i);
            viewHolder.seek = (SeekBar) view.findViewById(R.id.seek);
            viewHolder.seek.setTag(i);
            viewHolder.title = (TextView) view.findViewById(R.id.item_title);
            viewHolder.menu = (ImageView) view.findViewById(R.id.item_menu);
            Cursor cursor = db.query("best_table",null,"id="+music.getId(),null,null,null,null);
            if (cursor.moveToFirst()){
                viewHolder.menu.setImageResource(R.drawable.star);
                viewHolder.menu.setTag(1);
            } else viewHolder.menu.setTag(0);

            viewHolder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if((int)view.getTag() == 1){
                        db.delete("best_table","id=?",new String[]{""+music.getId()});
                        viewHolder.menu.setImageResource(R.drawable.add_to_best);

                    }else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("title",music.getTitle());
                        contentValues.put("id",music.getId());
                        contentValues.put("song_loc",music.getUrl());
                        db.insert("best_table",null,contentValues);
                        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                        viewHolder.menu.setImageResource(R.drawable.star);
                    }

//                    PopupMenu popupMenu = new PopupMenu(context,view);
//                    popupMenu.getMenuInflater().inflate(R.menu.popmenu,popupMenu.getMenu());
//
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menuItem) {
////                            Toast.makeText(context, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                            switch (menuItem.getItemId()){
//                                case R.id.best:
//                                    ContentValues contentValues = new ContentValues();
//                                    contentValues.put("title",music.getTitle());
//                                    contentValues.put("song_loc",music.getUrl());
//                                    db.insert("best_table",null,contentValues);
//                                    Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
//                                    break;
//                                case R.id.delete:
//                                    list.remove((int) view.getTag());
//                                    if(play_pos != -1){
//                                        item_media.get(play_pos).stop();
//                                        item_play.get(play_pos).setImageResource(R.drawable.play);
//                                        item_seek.get(play_pos).setProgress(0);
//                                    }
//                                    item_media = new ArrayList<>();
//                                    item_play = new ArrayList<>();
//                                    item_seek = new ArrayList<>();
//                                    notifyDataSetChanged();
//                                    flag = false;
//                                    play_pos = -1;
//                                    a = 0;
//                                    break;
//                            }
//                            return true;
//                        }
//                    });
//                    popupMenu.show();
                }
            });

            view.setTag(viewHolder);
        }else
            viewHolder =(ViewHolder) view.getTag();

        viewHolder.title.setText(music.getTitle());
//        mediaPlay = MediaPlayer.create(context, Uri.parse(music.getUrl()));
        mediaPlay = new MediaPlayer();
        Toast.makeText(context, "" + music.getUrl(), Toast.LENGTH_SHORT).show();
        try {
            mediaPlay.setDataSource("http://192.168.43.46/"+music.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                viewHolder.seek.setMax(mediaPlayer.getDuration());
                viewHolder.seek.setProgress(0);
                //mediaPlayer.start();
                //playCycle(viewHolder.seek,mediaPlayer);
            }
        });
        mediaPlay.prepareAsync() ;
        mediaPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(context, "klkkkkkk"+play_pos, Toast.LENGTH_SHORT).show();
                item_play.get(play_pos).setImageResource(R.drawable.play);
                mediaPlayer.seekTo(0);
                item_seek.get(play_pos).setProgress(0);
                if(++play_pos <= item_media.size()-1){
                    Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                    if(item_media.get(play_pos) != null){
                        item_play.get(play_pos).setImageResource(R.drawable.play);
                        mediaPlayer = item_media.get(play_pos);
                        item_play.get(play_pos).setImageResource(R.drawable.pause);
                        mediaPlayer.start();
                        playCycle(item_seek.get(play_pos),mediaPlayer);

                    }else {
                        play_pos++;
                        onCompletion(mediaPlayer);
                    }
                }else play_pos--;


            }
        });
        item_media.add(mediaPlay);
        item_play.add(viewHolder.play);


        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = item_media.get((int)view.getTag());
                if(!flag){
                    //mediaPlayer.start();
                    flag = true;
                    play_pos = (int) view.getTag();
                    //playCycle(item_seek.get(play_pos),mediaPlayer);
                }
                else if((int)view.getTag() != play_pos){
                    System.out.println(play_pos);
                    item_play.get(play_pos).setImageResource(R.drawable.play);
                    item_media.get(play_pos).pause();
                    //mediaPlayer.start();
                    flag = true;
                    play_pos = (int) view.getTag();

                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.play.setImageResource(R.drawable.play);
                }else {
                    mediaPlayer.start();
                    viewHolder.play.setImageResource(R.drawable.pause);
                }
                playCycle(item_seek.get(play_pos),mediaPlayer);
            }
        });

        viewHolder.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b)
                        item_media.get((int)seekBar.getTag()).seekTo(i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        item_seek.add(viewHolder.seek);

        return view;
    }
    public void playCycle(SeekBar seekBar, MediaPlayer mediaPlayer){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying()){
            final MediaPlayer finalMediaPlayer = mediaPlayer;
            final SeekBar finalSeekBar = seekBar;
            final SeekBar finalSeekBar1 = seekBar;
            final MediaPlayer finalMediaPlayer1 = mediaPlayer;
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle(finalSeekBar1, finalMediaPlayer1);
                }
            };
        }else return;
        handler.postDelayed(runnable,1000);
    }

    public  void finish(){
        if(play_pos != -1)
            item_media.get(play_pos).stop();
    }

}
