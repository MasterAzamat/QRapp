package com.example.azaat.qrapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Azaat on 04.04.2018.
 */

public interface ScreenInterface{
    @GET("aaaa.php")
    Call<List<Music>> getData(@Query("id_code") String ness);
}
