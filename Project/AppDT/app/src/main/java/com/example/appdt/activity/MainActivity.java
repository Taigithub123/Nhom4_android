package com.example.appdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appdt.R;
import com.example.appdt.adapter.loaisanphamAdapter;
import com.example.appdt.adapter.sanphamAdapter;
import com.example.appdt.model.loaisp;
import com.example.appdt.model.sanpham;

import com.example.appdt.retrofit.api;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewMainScreen;
    NavigationView navigationView;
    ListView listViewMainScreen;
    DrawerLayout drawerLayout;
    loaisanphamAdapter loaisanphamAdapter;
    List<loaisp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    api api;
    List<sanpham> mangsp;
    sanphamAdapter spAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find();
        ActionViewFlipper();
        ActionBar();
        getproduct();
        gettypeproduct();
    }
    private void setproduct(List<sanpham> array) {

        spAdapter = new sanphamAdapter(this, array);
        recyclerViewMainScreen.setAdapter(spAdapter);
    }
    private void getproduct() {
        api.opi.getsp().enqueue(new Callback<List<sanpham>>() {
            @Override
            public void onResponse(Call<List<sanpham>> call, Response<List<sanpham>> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                List<sanpham> abc = (List<sanpham>) response.body();
                setproduct((List<sanpham>) abc);
//                Log.e("lstP", String.valueOf(abc));
            }

            @Override
            public void onFailure(Call<List<sanpham>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void settypeproduct(List<loaisp> result) {

        loaisanphamAdapter = new loaisanphamAdapter(this, result);
        listViewMainScreen.setAdapter(loaisanphamAdapter);


    }
    private void gettypeproduct() {
        api.opi.getloaisp().enqueue(new Callback<List<loaisp>>() {
            @Override
            public void onResponse(Call<List<loaisp>> call, Response<List<loaisp>> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                List<loaisp> lsp = (List<loaisp>) response.body();
                settypeproduct((List<loaisp>) lsp);

            }

            @Override
            public void onFailure(Call<List<loaisp>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void ActionViewFlipper() {
        List<String> Banner = new ArrayList<>();
        Banner.add("https://artcity.vn/wp-content/uploads/2021/02/Thiet-ke-banner-quang-cao-dien-thoai-3.jpg");
        Banner.add("https://img3.thuthuatphanmem.vn/uploads/2019/10/08/banner-quang-cao-dien-thoai-dep_103211368.jpg");
        Banner.add("https://thietkehaithanh.com/wp-content/uploads/2019/06/huong-dan-thiet-ke-banner-dien-thoai-bang-photoshop.jpg");
        for(int i=0;i<Banner.size();i++){
            ImageView imgView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(Banner.get(i)).into(imgView);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imgView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setInAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void find() {
        toolbar = findViewById(R.id.toolbarMainScreen);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerViewMainScreen = findViewById(R.id.recycleviewMainScreen);
        navigationView = findViewById(R.id.navigationviewMainScreen);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewMainScreen.setLayoutManager(layoutManager);
        recyclerViewMainScreen.setHasFixedSize(true);

        listViewMainScreen = findViewById(R.id.listviewMainScreen);
        drawerLayout = findViewById(R.id.drawerLayout);

        mangloaisp = new ArrayList<>();
        mangsp = new ArrayList<>();

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}