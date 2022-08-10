package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ontopage extends AppCompatActivity {

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);

    }
    public  void login(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontopage);






    }
}