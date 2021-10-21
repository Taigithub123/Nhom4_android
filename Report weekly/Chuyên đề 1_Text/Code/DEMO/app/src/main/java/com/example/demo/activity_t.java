package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class activity_t extends AppCompatActivity {
    Button btnClickHere;
    EditText editTextTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        btnClickHere=(Button)findViewById(R.id.btnClickHere);
        editTextTime=(EditText)findViewById(R.id.editTextTime);
        btnClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat format= new SimpleDateFormat("hh:mm:ss");
                String time="Time: "+ format.format(calendar.getTime());
                EditText textView= findViewById(R.id.editTextTime);
                textView.setText(time);

            }
        });
    }
}