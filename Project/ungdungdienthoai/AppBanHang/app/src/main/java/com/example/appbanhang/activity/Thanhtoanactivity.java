package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.appbanhang.R;
import com.example.appbanhang.retrofit.AppBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.internal.Util;


public class Thanhtoanactivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttongtien, txtsodt, txtemail;
    EditText editdiachi;
    AppCompatButton btnthanhtoan;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    AppBanHang appBanHang;
    long tongtien;
    int totalItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        countItem();
        initView();
        initControl();
    }

    private void countItem() {
        totalItem = 0;
        for (int i=0; i<utils.manggiohang.size(); i++){
            totalItem = totalItem + utils.manggiohang.get(i).getSoluong();
        }

    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien =getIntent().getLongExtra("tongtien",0);
        txttongtien.setText(decimalFormat.format(tongtien));
        txtemail.setText(utils.user_current.getEmail());
        txtsodt.setText(utils.user_current.getMobile());
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_diachi =editdiachi.getText().toString().trim();
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else{
                    String str_email=utils.user_current.getEmail();
                    String str_sdt=utils.user_current.getMobile();
                    int id= utils.user_current.getUser_id();

                    Log.d("test",new Gson().toJson(utils.manggiohang));
                    compositeDisposable.add(appBanHang.createOder(str_email,str_sdt,String.valueOf(tongtien) , id, str_diachi,totalItem, new Gson().toJson(utils.manggiohang) )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_LONG).show();

                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(),throwable.getMessage() , Toast.LENGTH_LONG).show();
                                    }
                            ));
                }
            }
        });
    }




    private void initView() {
        appBanHang= RetrofitClient.getInstance(utils.BASE_URL).create(AppBanHang.class);
        toolbar=findViewById(R.id.toolbar);
        txttongtien=findViewById(R.id.txttongtien);
        txtsodt=findViewById(R.id.txtsdt);
        txtemail=findViewById(R.id.txtemail);
        editdiachi=findViewById(R.id.edtdiachi);
        btnthanhtoan=findViewById(R.id.btndathang);

    }


}