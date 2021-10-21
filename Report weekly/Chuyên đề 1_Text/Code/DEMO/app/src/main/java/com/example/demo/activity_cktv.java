package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.Toast;

public class activity_cktv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cktv);
        final CheckedTextView checkedTextView= findViewById(R.id.checked_textView);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView.toggle();
                if(checkedTextView.isChecked()){
                    Toast.makeText(activity_cktv.this, "checked", Toast.LENGTH_SHORT).show();
                }else{
                Toast.makeText(activity_cktv.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}