package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appbanhang.retrofit.AppBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.utils;

import com.example.appbanhang.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResetPassWordActivity extends AppCompatActivity {
    EditText email;
    AppCompatButton btnreset;
    AppBanHang appBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_word);
        initview();
        initControl();

    }

    private void initview() {
        appBanHang = RetrofitClient.getInstance(utils.BASE_URL).create(AppBanHang.class);
        email = findViewById(R.id.editresetpass);
        btnreset = findViewById(R.id.btndoimatkhau);
        progressBar=findViewById(R.id.progressBar);
    }

    private void initControl() {
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email=email.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập gmail", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    compositeDisposable.add(appBanHang.resetpass(str_email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if(userModel.isSuccess()){
                                            Toast.makeText(getApplicationContext(),userModel.getMessage() , Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(getApplicationContext(),dangnhapctivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(),userModel.getMessage() , Toast.LENGTH_LONG).show();

                                        }
                                        progressBar.setVisibility(View.VISIBLE);
                                    },
                                        throwable->{
                                            Toast.makeText(getApplicationContext(),throwable.getMessage() , Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                        }
                ));

                }
            }
        });

    }
        @Override
        protected void onDestroy () {
            compositeDisposable.clear();
            super.onDestroy();
        }
    }
