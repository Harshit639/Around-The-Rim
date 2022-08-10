package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

public class SignUp extends AppCompatActivity {

    EditText name;
    EditText pass;

    Button sign;

    public void onclick(View view){
        ParseUser.logOut();
        Intent intent = new Intent(getApplicationContext(),verification.class);
        intent.putExtra("username",name.getText().toString());
        intent.putExtra("password",pass.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name= findViewById(R.id.lol1);
        pass=findViewById(R.id.lol2);

        sign=findViewById(R.id.butt);
    }
}