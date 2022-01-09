package com.example.appbanhang.retrofit;

import com.example.appbanhang.model.DonHang;
import com.example.appbanhang.model.DonHangModel;
import com.example.appbanhang.model.LoaiSpModel;
import com.example.appbanhang.model.SanPhamMoiModel;
import com.example.appbanhang.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppBanHang {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
            @Field("page") int page,
            @Field("loai") int loai
    );
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangkytk(
            @Field("email") String email,
            @Field("password") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("password") String pass

    );
    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetpass(
            @Field("email") String email
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> createOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet



    );
    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemdonhang(
            @Field("iduser") int id
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> tiemkiemsp(
            @Field("search") String search
    );


}
