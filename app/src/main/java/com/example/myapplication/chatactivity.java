package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class chatactivity extends AppCompatActivity implements View.OnClickListener  {

    ArrayAdapter arrayAdapter;
    String activeuser;
    ArrayList<String> messages = new ArrayList<>();
    Handler handler;
    ImageView image;

    public void messageupdate(){
        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("message");
        query1.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("reciever",activeuser);
        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("message");
        query2.whereEqualTo("reciever",ParseUser.getCurrentUser().getUsername());
        query2.whereEqualTo("sender",activeuser);
        List<ParseQuery<ParseObject>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        ParseQuery<ParseObject> query = ParseQuery.or(queries);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null){
                    if(objects.size()>0){
                        messages.clear();

                        for (ParseObject messagess : objects){
                            String messagecontent = messagess.getString("message");
                            if(!messagess.getString("sender").equals(ParseUser.getCurrentUser().getUsername())){
                                messagecontent= "> " + messagecontent;
                            }
                            Log.i("info",messagecontent);
                            messages.add(messagecontent);
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        messageupdate();
                    }
                }, 5000);
            }
        });
    }

    public  void sendchat(View view){
        EditText editText = findViewById(R.id.chattext);
        String content = editText.getText().toString();
        ParseObject message = new ParseObject("message");
        message.put("sender", ParseUser.getCurrentUser().getUsername().toString());
        message.put("reciever",activeuser);
        message.put("message",editText.getText().toString());
        editText.setText("");

        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(chatactivity.this, "message sent", Toast.LENGTH_SHORT).show();
                    messages.add(content);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatactivity);

        image=findViewById(R.id.back);
        image.setOnClickListener(this);



        Intent intent = getIntent();
        activeuser= intent.getStringExtra("nameofselected");
        setTitle("Chat with "+activeuser);
        ListView chat = findViewById(R.id.chatarea);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,messages);
        chat.setAdapter(arrayAdapter);
        messageupdate();


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back){
            Intent net = new Intent(getApplicationContext(), chatlist.class);
            startActivity(net);
        }
    }
}