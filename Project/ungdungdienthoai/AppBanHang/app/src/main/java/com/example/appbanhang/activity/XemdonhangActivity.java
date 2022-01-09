package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.appbanhang.R;
import com.example.appbanhang.adapter.DonhangAdapter;
import com.example.appbanhang.model.DonHang;
import com.example.appbanhang.model.SanPhamMoi;
import com.example.appbanhang.retrofit.AppBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemdonhangActivity extends AppCompatActivity {
CompositeDisposable compositeDisposable= new CompositeDisposable();
AppBanHang appBanHang;
RecyclerView redonhang;
Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdonhang);
        initView();
       Actionbar();
       getOrder();



   }
    private void getOrder() {
        compositeDisposable.add(appBanHang.xemdonhang(utils.user_current.getUser_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donHangModel->{

                                DonhangAdapter adapter = new DonhangAdapter(getApplicationContext(),donHangModel.getResult() );
                                redonhang.setAdapter(adapter);


                        },
                        Throwable->{

                        }
                ));
    }

    private void Actionbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// cai nay có quan trọng k
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void initView() {
        toolbar =findViewById(R.id.toolbar);
        appBanHang = RetrofitClient.getInstance(utils.BASE_URL).create(AppBanHang.class);
        redonhang=findViewById(R.id.recyclerview_donhang);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        redonhang.setLayoutManager(layoutManager);


    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}