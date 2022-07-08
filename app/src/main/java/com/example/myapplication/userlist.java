package com.example.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class userlist extends AppCompatActivity {

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getphoto();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.share){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }else{
                getphoto();
            }
        }else if(item.getItemId()==R.id.logout){
            ParseUser.logOut();
            Intent net = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(net);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);


        ListView list = findViewById(R.id.listview);
        ArrayList<NumbersView> usernames = new ArrayList<NumbersView>();
        NumbersViewAdapter arrayAdapter = new NumbersViewAdapter(this,usernames);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for (ParseUser user : objects){
                            usernames.add(new NumbersView(R.drawable.img_2,user.getUsername()));

                        }
                        list.setAdapter(arrayAdapter);}}
                    else{
                            e.printStackTrace();
                        }
                    }
                });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("username",(usernames.get(i).getNumberInDigit()));
                startActivity(intent);

            }
        });

    }
}