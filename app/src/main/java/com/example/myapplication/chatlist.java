 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

 public class chatlist extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViews;
    adaptter asd;
    ArrayList<frontpage> item;
    ImageView photo,back;
     ArrayList<String> messages;
//     Handler handler;

     LinearLayoutManager layoutManagera;

//
//     public void messageupdate(){
//         ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("message");
//         query1.whereEqualTo("sender", ParseUser.getCurrentUser().getUsername());
//         query1.whereEqualTo("reciever", activeuser);
//         ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("message");
//         query2.whereEqualTo("reciever", ParseUser.getCurrentUser().getUsername());
//         query2.whereEqualTo("sender", activeuser);
//         List<ParseQuery<ParseObject>> queries = new ArrayList<>();
//         queries.add(query1);
//         queries.add(query2);
//         ParseQuery<ParseObject> query = ParseQuery.or(queries);
//         query.orderByAscending("createdAt");
//
//         query.findInBackground(new FindCallback<ParseObject>() {
//             @Override
//             public void done(List<ParseObject> objects, ParseException e) {
//
//                 if (e == null) {
//                     if (objects.size() > 0) {
//                         messages.clear();
//
//                         for (ParseObject messagess : objects) {
//                             String messagecontent = messagess.getString("message");
//                             if (!messagess.getString("sender").equals(ParseUser.getCurrentUser().getUsername())) {
//                                 messagecontent = "> " + messagecontent;
//                             }
//                             Log.i("info", messagecontent);
//                             messages.add(messagecontent);
//                         }
//                         recyclerViews.setAdapter(asd);
//                     }
//                 }
//                 handler = new Handler();
//                 handler.postDelayed(new Runnable() {
//                     @Override
//                     public void run() {
//                         messageupdate();
//                     }
//                 }, 5000);
//             }
//         });
//
// }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);


        item = new ArrayList<frontpage>();
        messages = new ArrayList<>();




        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e==null && objects.size()>0){

                        for (ParseUser user : objects){
                            messages.add(user.getUsername());

                           // Log.i("info",user.getUsername());
                        }
                       // Log.i("info", String.valueOf(messages));
//                    Log.i("hello", String.valueOf(messages));
                    for ( String activeuser : messages ) {
                        Log.i("usernamefdsfvdfv",activeuser);
                    }
                    for ( String activeuser : messages ) {

                        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("message");
                        query1.whereEqualTo("sender", ParseUser.getCurrentUser().getUsername());
                        query1.whereEqualTo("reciever", activeuser);
                        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("message");
                        query2.whereEqualTo("reciever", ParseUser.getCurrentUser().getUsername());
                        query2.whereEqualTo("sender", activeuser);
                        List<ParseQuery<ParseObject>> queries = new ArrayList<>();
                        queries.add(query1);
                        queries.add(query2);
                        ParseQuery<ParseObject> queryl = ParseQuery.or(queries);
                        queryl.orderByDescending("createdAt");

                        queryl.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {

                                if (e == null) {
                                    if (objects.size() > 0) {
                               //         messages.clear();

                                        for (ParseObject messagess : objects) {
                                            String messagecontent = messagess.getString("message");
                                            if (!messagess.getString("sender").equals(ParseUser.getCurrentUser().getUsername())) {
                                                messagecontent = "> " + messagecontent;
                                            }
                                            Log.i("info", messagecontent);
                                            item.add(new frontpage(activeuser,messagecontent));
                                            break;
                                        }
                                        recyclerViews.setAdapter(asd);
                                        asd.notifyDataSetChanged();
                                    }else{
                                        Log.i("no messages",activeuser);
                                        item.add(new frontpage(activeuser,"No messages"));
                                    }
                                    recyclerViews.setAdapter(asd);
                                    asd.notifyDataSetChanged();
                                }
//                    handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            messageupdate();
//                        }
//                    }, 5000);
                            }
                        });
                    }


                        }
            }
        });

        Log.i("batabhai", String.valueOf(item.size()));

            //trial









        recyclerViews = findViewById(R.id.recycler);
        layoutManagera = new LinearLayoutManager(this);
        layoutManagera.setOrientation(RecyclerView.VERTICAL);
        recyclerViews.setLayoutManager(layoutManagera);
        asd = new adaptter(item);
        recyclerViews.setAdapter(asd);
        back=findViewById(R.id.back);
        photo=findViewById(R.id.addphotoback);
        back.setOnClickListener(this);
        photo.setOnClickListener(this);

        asd.notifyDataSetChanged();
        Log.i("info" , String.valueOf(item));



        /*StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());*/
    }


     @Override
     public void onClick(View v) {

             if(v.getId()==R.id.addphotoback){
                 Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 startActivityForResult(intent,1);
             }else if(v.getId()==R.id.back){
                 finish();
                // Intent intent = new Intent(getApplicationContext(),feedtesting.class);
               //  startActivity(intent);
             }

     }
 }