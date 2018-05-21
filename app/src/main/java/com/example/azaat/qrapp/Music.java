package com.example.azaat.qrapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Azaat on 28.03.2018.
 */

public class Music {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private  String title;
    @SerializedName("url")
    private String url;

    public Music(int id, String name, String title, String url) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
