package com.example.azaat.qrapp;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azaat on 04.04.2018.
 */

public class NewsClass {
    @SerializedName("articles")
    private ArrayList<articles> articles;

    public ArrayList<articles> getArticles() {
        return articles;
    }
}
class articles{
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("urlToImage")
    private String img;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }
}