package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        @SuppressLint("HardwareIds")
        String ID = Settings.Secure.getString(getContentResolver(),
        Settings.Secure.ANDROID_ID);
        textView.setText("Device ID: "+ ID);
    }





    public void Btn_reqconn(View view) {
        Intent connint = new Intent(getApplicationContext(), BluetoothMain.class);
        startActivity(connint);

    }
}
