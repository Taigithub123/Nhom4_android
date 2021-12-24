package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appbanhang.R;

public class dangkiactivity extends AppCompatActivity {
EditText email, pass, repass;
AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkiactivity);
        initView();
        initControl();
    }
    private void initView(){
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        repass=findViewById(R.id.repass);
        button=findViewById(R.id.btndangky);
    }
    private void initControl(){
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dangky();
    }
});
    }
    private void dangky(){
        String str_email
    }
}