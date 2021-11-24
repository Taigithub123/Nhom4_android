package com.example.appdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.appdt.retrofit.Client;
import com.example.appdt.utils.checkconnect;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        api = Client.getInstance(checkconnect.BASE_URL).create(api.class);
        find();

        ActionBar();
        if(isConnected(this)){

            ActionViewFlipper();
            gettypeproduct();
            getproduct();

        }else{
            Toast.makeText(getApplicationContext(),"khong co internet",Toast.LENGTH_LONG).show();
        }
    }
    private void getproduct() {
        compositeDisposable.add(api.getsp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanphammodel -> {
                            if (sanphammodel.isSuccess()){
                                mangsp = sanphammodel.getResult();
                                spAdapter = new sanphamAdapter(getApplicationContext(),mangsp);
                                recyclerViewMainScreen.setAdapter(spAdapter);


                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"Khong ket noi duoc voi server"+ throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }
    private void gettypeproduct() {

        compositeDisposable.add(api.getloaisp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaispmodel -> {
                            if (loaispmodel.isSuccess()){
                                mangloaisp = loaispmodel.getResult();
                                loaisanphamAdapter = new loaisanphamAdapter(getApplicationContext(),mangloaisp);
                                listViewMainScreen.setAdapter(loaisanphamAdapter);
                            }
                        }
                ));

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
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                return false;
            }
        }

        return false;

    }
//    private boolean isConnected(Context context){
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected()) ){
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}