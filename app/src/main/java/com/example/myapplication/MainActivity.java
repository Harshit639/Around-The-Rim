package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    //8fuPPgArqlOz
    EditText name;
    EditText pass;
    Button log;
    Button sign;
    Boolean sl = true;
    ImageView back;
    ImageView lol;

    public void function(){
        Intent intent = new Intent(getApplicationContext(),feedtesting.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if( view.getId()==R.id.imageView4){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            slogin(view);
        }
        return false;
    }

//    public void onclickx(View view) {
//        if (view.getId() == R.id.button2) {
//
//            if (sl) {
//                log.setText("Sign Up");
//                sign.setText("Log In");
//                sl = false;
//            } else {
//                log.setText("Log In");
//                sign.setText("Don't have a account? Sign Up");
//                sl = true;
//            }
//        }
//    }


    public void slogin(View view) {
        if (name.getText().toString().matches("") || pass.getText().toString().matches("")) {
            Toast.makeText(this, "A username and password required", Toast.LENGTH_SHORT).show();
        } else {
            if (1==1) {
                ParseUser.logInInBackground(name.getText().toString(), pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null){
                            Log.i("INFO","Succes");
                            function();
                        }else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                ParseUser.logOut();
                Intent intent = new Intent(getApplicationContext(),verification.class);
                intent.putExtra("username",name.getText().toString());
                intent.putExtra("password",pass.getText().toString());
                startActivity(intent);

//                ParseUser user = new ParseUser();
//                user.setUsername(name.getText().toString());
//                user.setPassword(pass.getText().toString());
//
//                user.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if(e==null){
//                            Log.i("info", "success");
//                            function();
//                        }else{
//                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        name= findViewById(R.id.username);
        pass=findViewById(R.id.password);
        log=findViewById(R.id.button);
     //   sign=findViewById(R.id.button2);
       // back=findViewById(R.id.imageView2);
     //   lol=findViewById(R.id.imageView2);
    //    back.setOnClickListener(this);
    //    lol.setOnClickListener(this);
        pass.setOnKeyListener(this);

        if(ParseUser.getCurrentUser() != null){
            function();
        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


}
