package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Handler handler;
            handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(getApplicationContext(),ontopage.class);
                    startActivity(intent);
                    finish();
                }
            },3000);

        }
    }
