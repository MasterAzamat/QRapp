package com.example.azaat.qrapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Azaat on 04.04.2018.
 */

public interface NewsInterface {
    @GET("top-headlines?country=us&category=business&apiKey=1b1cd8ad93b34a1399ac847348095fba")
    Call<NewsClass> classForUse();
}
