package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class feed extends AppCompatActivity {

    public RecyclerView recyclerView;
    private ArrayList<proceed> arrayList;
   // LinearLayout test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);  //yaha change kar dena


        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.Recycle);
/*

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");

        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null){
                    for(ParseObject object : objects){

                        ParseFile file = (ParseFile) object.get("image");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e==null && data!=null) {
                                    Log.i("info","doing");
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    ImageView imageView = new ImageView(getApplicationContext());
                                    imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT
                                    ));
                                    imageView.setImageBitmap(bitmap);
//                                    test.addView(imageView);
                                }
                            }
                        });

                    }
                }
            }
        });
      */  //testing

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
     //   query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
     //   query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for (ParseUser user : objects){
                            Log.i("dcds","try");
                            Log.i("gfd",user.getUsername());
                            arrayList.add(new proceed(user.getUsername(),R.drawable.fest));
                            break;

                        }
                        }}
                else{
                    e.printStackTrace();
                }
            }
        });







        //test= findViewById(R.id.test);


     arrayList.add(new proceed("scdsc",R.drawable.fest));
      arrayList.add(new proceed("scdsc",R.drawable.fest));
       RecycleAdapter recycleAdapter = new RecycleAdapter(arrayList);

        recyclerView.setAdapter(recycleAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}