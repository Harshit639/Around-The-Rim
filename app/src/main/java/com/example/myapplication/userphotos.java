package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class userphotos extends AppCompatActivity {

    ArrayList<Bitmap> list;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UserRecyclerAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userphotos);


        // fo user photos

        list= new ArrayList<>();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereEqualTo("username", username);
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
                                    list.add(bitmap);
                                }
                                adap.notifyDataSetChanged();
                            }
                        });

                    }
                }
            }
        });

        recyclerView= findViewById(R.id.lolou);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adap = new UserRecyclerAdapter(list);
        recyclerView.setAdapter(adap);

    }
}