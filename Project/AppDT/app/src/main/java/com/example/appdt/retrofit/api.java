package com.example.appdt.retrofit;

import com.example.appdt.model.loaispmodel;
import com.example.appdt.model.sanphammodel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface api {
    @GET("gettypeproduct.php")
    Observable<loaispmodel> getloaisp();

    @GET("getproduct.php")
    Observable<sanphammodel> getsp();
}
