package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.List;

public class feedtesting extends AppCompatActivity implements View.OnClickListener {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<modelclass> list;
    ArrayList<String> tests;
    adapter adap;
    ImageView addphoto;
    ImageView users;
    ImageView send;
    ImageView self;

    public void getphoto(){
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
                ParseObject object = new ParseObject("Image");
                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.i("info","done");
                            Toast.makeText(feedtesting.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }else{
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.addphoto)) {
            getphoto();
        } else if (v.getId() == R.id.userlist) {
            Intent net = new Intent(getApplicationContext(), userlist.class);
            startActivity(net);
        } else if (v.getId() == R.id.sendwe) {
            Intent net = new Intent(getApplicationContext(), chatlist.class);
            startActivity(net);
        } else if (v.getId() == R.id.self) {
            Intent net = new Intent(getApplicationContext(), PersonalProfile.class);
            startActivity(net);
        }
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.share_menu,menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.share){
//            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
//            }else{
//                getphoto();
//            }
//        }else if(item.getItemId()==R.id.logout){
//            ParseUser.logOut();
//            Intent net = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(net);
//        }else if(item.getItemId()==R.id.photos){
//            Intent net = new Intent(getApplicationContext(), userlist.class);
//            startActivity(net);
//        }else if(item.getItemId()==R.id.sendwe){
//            Intent net = new Intent(getApplicationContext(), userlist.class);
//            startActivity(net);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedtesting);



        initdata();
        initrecyclerview();
        addphoto = findViewById(R.id.addphoto);
        users = findViewById(R.id.userlist);
        send=   findViewById(R.id.sendwe);
        self=findViewById(R.id.self);
        users.setOnClickListener(this);
        addphoto.setOnClickListener(this);
        send.setOnClickListener(this);
        self.setOnClickListener(this);
        
    }

    private void initdata() {
        list = new ArrayList<modelclass>();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if( e==null){
                    if(objects.size()>0){
                    for(ParseObject object : objects){

                        String name = object.getString("username");
                        ParseFile file = (ParseFile) object.get("image");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e==null && data!=null) {
                                    Log.i("info","doing");
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    Bitmap b = Bitmap.createScaledBitmap(bitmap, 360,428, true);
                                    ImageView imageView = new ImageView(getApplicationContext());
                                    imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT
                                    ));
                                    imageView.setImageBitmap(bitmap);
                                    list.add(new modelclass(name,b));
//                                    test.addView(imageView);
                                }
                                recyclerView.setAdapter(adap);
                            }
                        });
                        Log.i("infoSCDS",String.valueOf(list.size()));

                    }

                }
            }
        }});



































/*

        list = new ArrayList<modelclass>();
        tests = new ArrayList<>();
        ParseQuery<ParseUser> query2 = ParseUser.getQuery();
        //   query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        //   query.addAscendingOrder("username");

        query2.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for (ParseUser user : objects){
                            Log.i("dcds","try");
                            String x = user.getUsername();
                            Log.i("gfd",x);
                            tests.add(x);
                           // Log.i("fc", String.valueOf(list));
                            list.add(new modelclass(x,R.drawable.fest));


                        }
                        recyclerView.setAdapter(adap);
                    }}
                else{
                    e.printStackTrace();
                }
            }
        });
     //   list.add(new modelclass("vdfvdf",R.drawable.fest));
     //   list.add(new modelclass("vdfvdf",R.drawable.fest));

        for(String x : tests){
            Log.i("dffvd",x);
        } */
    }

    private void initrecyclerview() {

        recyclerView= findViewById(R.id.recyclerviewtest);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adap = new adapter(list);

        adap.notifyDataSetChanged();
    }

}