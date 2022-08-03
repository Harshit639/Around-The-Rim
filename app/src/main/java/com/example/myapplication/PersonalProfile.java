package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class PersonalProfile extends AppCompatActivity {

    TextView currentuser;
    ImageView a1, a2, a3, a4;
    ArrayList<Bitmap> list ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        a1 = findViewById(R.id.imageView20);
        a2 = findViewById(R.id.imageView21);
        a3 = findViewById(R.id.imageView22);
        a4 = findViewById(R.id.imageView23);

        currentuser = findViewById(R.id.textView4);
        currentuser.setText(ParseUser.getCurrentUser().getUsername());
        list= new ArrayList<>();


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (objects.size() > 0 && e == null) {


                    try {


                        ParseFile file = (ParseFile) objects.get(0).get("image");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null && data != null) {
                                    Log.i("info", "doing");
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                    a1.setImageBitmap(bitmap);



                                }
                            }
                        });}catch (Exception E){
                        E.printStackTrace();
                    }
//                    try {
//                        ParseFile file2 = (ParseFile) objects.get(2).get("image");
//                        file2.getDataInBackground(new GetDataCallback() {
//                            @Override
//                            public void done(byte[] data, ParseException e) {
//                                if (e == null && data != null) {
//                                    Log.i("info", "doing");
//                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//                                    a2.setImageBitmap(bitmap);
//
//
//                                }
//                            }
//                        });
//                    }catch (Exception E){}
//                    try{
//                    ParseFile file3 = (ParseFile) objects.get(3).get("image");
//                    file3.getDataInBackground(new GetDataCallback() {
//                        @Override
//                        public void done(byte[] data, ParseException e) {
//                            if (e == null && data != null) {
//                                Log.i("info", "doing");
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//                                a3.setImageBitmap(bitmap);
//
//
//                            }
//                        }
//                    });}catch (Exception E){}
//                    try{
//                    ParseFile file4 = (ParseFile) objects.get(4).get("image");
//                    file4.getDataInBackground(new GetDataCallback() {
//                        @Override
//                        public void done(byte[] data, ParseException e) {
//                            if (e == null && data != null) {
//                                Log.i("info", "doing");
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//                                a4.setImageBitmap(bitmap);
//
//
//                            }
//                        }
//                    });}catch(Exception E){
//
//                    }
          //          }

//                    for (ParseObject object : objects) {
//
//                        ParseFile file = (ParseFile) object.get("image");
//                        file.getDataInBackground(new GetDataCallback() {
//                            @Override
//                            public void done(byte[] data, ParseException e) {
//                                if (e == null && data != null) {
//                                    Log.i("info", "doing");
//                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//                                    list.add(bitmap);
//                                    list.
//
//
//                                }
//                            }
//                        });


                    }
             //       for(int i=0;i<list.size();i++){
//                        try{
//
//                        }
                    }
           //     }


          //  }


        });
    }
}


