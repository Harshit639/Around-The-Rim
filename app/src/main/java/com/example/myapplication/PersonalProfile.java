package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalProfile extends AppCompatActivity {

    TextView currentuser,totalposts;
    ImageView a1;
    ArrayList<Bitmap> list ;
    Button setprofile,updateprofile;


    public void updatephoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,2);
    }



    public void setphoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Uri selectedimage =data.getData();
        if(requestCode==1 && resultCode==RESULT_OK){
            try{
                Bitmap bitmap =MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedimage);
                ByteArrayOutputStream stream =new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
                byte[] bytearray = stream.toByteArray();
                ParseFile file = new ParseFile("image.png",bytearray);
                ParseObject object = new ParseObject("ProfileImage");

                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.i("info","done");
                            Toast.makeText(PersonalProfile.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                            setprofile.setVisibility(View.INVISIBLE);
                            updateprofile.setVisibility(View.VISIBLE);
                        }else{
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedimage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream =new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
            byte[] bytearray = stream.toByteArray();
            ParseFile fileo = new ParseFile("image.png",bytearray);
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ProfileImage");
            query.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    ParseObject object = objects.get(0);
                    Log.i("info", String.valueOf(objects.size()));
                        object.put("image",fileo);
                        object.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    //success, saved!
                                    Log.d("MyApp", "Successfully saved!");
                                } else {
                                    //fail to save!
                                    e.printStackTrace();
                                }
                            }
                        });


                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        a1 = findViewById(R.id.imageView20);
        setprofile=findViewById(R.id.button2);
        totalposts=findViewById(R.id.textView6);
        updateprofile=findViewById(R.id.updatebutton);
//        a2 = findViewById(R.id.imageView21);
//        a3 = findViewById(R.id.imageView22);
//        a4 = findViewById(R.id.imageView23);

        currentuser = findViewById(R.id.textView4);
        currentuser.setText(ParseUser.getCurrentUser().getUsername());
        list= new ArrayList<>();


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ProfileImage");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                totalposts.setText("Total Posts: "+objects.size());

                if (objects.size() > 0 && e == null) {

                    setprofile.setVisibility(View.INVISIBLE);
                    updateprofile.setVisibility(View.VISIBLE);



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


                    }else{
                    setprofile.setVisibility(View.VISIBLE);
                    updateprofile.setVisibility(View.INVISIBLE);
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


