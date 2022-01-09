package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.retrofit.AppBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.utils;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class dangnhapctivity extends AppCompatActivity {
TextView dangky, quenmatkhau;
EditText email, pass;
Button btndangnhap;
AppBanHang appBanHang;
CompositeDisposable compositeDisposable= new CompositeDisposable();
Boolean islogin = false;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapctivity);
intView();
initcontrol();
    }
private void initcontrol(){
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), dangkiactivity.class);
                startActivity(intent);
            }
        });
        quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ResetPassWordActivity.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email=email.getText().toString().trim();
                String str_pass=pass.getText().toString().trim();
                if (TextUtils.isEmpty(str_email)) {
                    Toast.makeText(getApplicationContext(), "bạn chưa nhập email", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(str_pass)) {
                    Toast.makeText(getApplicationContext(), "bạn chưa nhập mật khẩu", Toast.LENGTH_LONG).show();
                }else {
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);
                    dangnhap(str_email, str_pass );
                }
            }
        });
    }
    private void intView(){
         Paper.init(this);
        appBanHang= RetrofitClient.getInstance(utils.BASE_URL).create(AppBanHang.class);
        email=findViewById(R.id.emaildn);
        quenmatkhau=findViewById(R.id.quenmatkhau);
        pass=findViewById(R.id.passdn);
        btndangnhap=findViewById(R.id.btndangnhap);
        dangky=findViewById(R.id.dangky);
        quenmatkhau=findViewById(R.id.quenmatkhau);
        if(Paper.book().read("email")!=null && Paper.book().read("pass")!=null){
            email.setText(Paper.book().read("email"));
        pass.setText(Paper.book().read("pass"));
        if(Paper.book().read("islogin")!=null){
            boolean flag=Paper.book().read("islogin");
            if(flag){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dangnhap(Paper.book().read("email"), Paper.book().read("pass"));
                    }
                }, 1000);
            }
        }
        }
    }
    private void dangnhap(String email, String pass){
        compositeDisposable.add(appBanHang.dangnhap(email,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                islogin =true;
                                Paper.book().write("islogin", islogin);
                                utils.user_current=userModel.getResult().get(0);
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
                            }
                        },
                        throwable->{
                            Toast.makeText(getApplicationContext(),throwable.getMessage() , Toast.LENGTH_LONG).show();
                        }
                ));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(utils.user_current.getEmail()!=null && utils.user_current.getPassword()!=null);
        email.setText(utils.user_current.getEmail());
        pass.setText(utils.user_current.getPassword());
    }
}