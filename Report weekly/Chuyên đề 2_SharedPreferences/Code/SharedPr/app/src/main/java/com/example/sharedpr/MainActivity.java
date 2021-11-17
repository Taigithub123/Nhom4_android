package com.example.sharedpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etData;
    Button btnSave;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("Example",Context.MODE_PRIVATE);

        etData = (EditText) findViewById(R.id.et_data);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);
        String savedData = sharedPreferences.getString("DATA", "");

        etData.setText(savedData);

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }
    public void onClick(View view) {
        if (view == btnSave) {
            SharedPreferences sharedPreferences = getSharedPreferences("Example",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // Nếu click vào nút Save, ta sẽ lưu dữ liệu lại.
            String data = etData.getText().toString();
            // "DATA" là key, data tham số thứ 2 là value.
            editor.putString("DATA", data);
            editor.commit();
            Toast.makeText(this,"Lưu thành công",Toast.LENGTH_LONG).show();
        } else if (view == btnClear) {
            SharedPreferences sharedPreferences = getSharedPreferences("Example",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // Nếu click vào nút Clear, ta sẽ xoá dữ liệu đi.
            etData.setText("");
            editor.clear();
            editor.commit();
            Toast.makeText(this,"Xoá thành công",Toast.LENGTH_LONG).show();
        }
    }

}