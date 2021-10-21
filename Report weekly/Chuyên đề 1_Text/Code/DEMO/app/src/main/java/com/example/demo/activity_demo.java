package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class activity_demo extends AppCompatActivity {
    Button submit;
    EditText number, testPassWord, numberPassWord, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        number = (EditText) findViewById(R.id.editTextNumber);
        date = (EditText) findViewById(R.id.editTextDate);
        testPassWord = (EditText) findViewById(R.id.editTextTextPassword);
        numberPassWord = (EditText) findViewById(R.id.editTextNumberPassword);
        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number.getText().toString().isEmpty()  ||  date.getText().toString().isEmpty()
                        || testPassWord.getText().toString().isEmpty()|| numberPassWord.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "number -  " + number.getText().toString() + " \n" + "Password -  " + testPassWord.getText().toString()+ " \n" + "NPassword -  " + numberPassWord.getText().toString()
                             + " \n" + "Date -  " + date.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}