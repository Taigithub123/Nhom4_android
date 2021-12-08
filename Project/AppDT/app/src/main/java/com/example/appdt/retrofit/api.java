package com.example.appdt.retrofit;

import com.example.appdt.model.loaisp;
import com.example.appdt.model.sanpham;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    api opi = new Retrofit.Builder()
            .baseUrl("https://webandroi1.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api.class);
//    @GET("Appdt/getproduct.php")
//    Observable<loaispmodel> getloaisp();

    @GET("Appdt/getproduct.php")
    Call<List<sanpham>> getsp();
    @GET("Appdt/gettypeproduct.php")
    Call<List<loaisp>> getloaisp();
    @POST("Appdt/chitiet.php")
    @FormUrlEncoded
    Observable<sanpham> getSanpham(
            @Field("page") int page,
            @Field("loai") int loai
    );
}
