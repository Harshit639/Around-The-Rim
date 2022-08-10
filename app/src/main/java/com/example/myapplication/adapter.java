package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{

    private ArrayList<modelclass> arrayList;


    public adapter(ArrayList<modelclass> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeditem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
       String text = arrayList.get(position).gettextview1();
       Bitmap resource = arrayList.get(position).getimageview1();
       int heart = arrayList.get(position).getheart();
       final String[] likes = {arrayList.get(position).getlikes()};

        holder.setData(text,resource,heart, likes[0]+" likes",resource);
        holder.profile.setImageResource(R.drawable.asd);

        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("ProfileImage");
        query2.whereEqualTo("username",text);

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if( e==null){
                    if(objects.size()>0){
                        Log.i("ifo", String.valueOf(objects.size()));

                            ParseFile file = (ParseFile) objects.get(0).get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null) {
                                        Log.i("info","doing");
                                       Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                       Bitmap b = Bitmap.createScaledBitmap(bitmap, 360,428, true);
                                        holder.setData(text,resource,heart, likes[0]+" likes",bitmap);
                                    }
                                }
                            });


                    }else{
                      //  holder.setData(text,resource,heart, likes[0]+" likes",resource);
                        holder.profile.setImageResource(R.drawable.asd);
                    }
                }
            }});

        holder.speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent net = new Intent(v.getContext(), chatactivity.class);
                 net.putExtra("nameofselected",text);
                v.getContext().startActivity(net);
            }
        });
       holder.saved.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(v.getContext(), "Post Saved", Toast.LENGTH_SHORT).show();
           }
       });
       holder.heart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               holder.heart.setClickable(false);
               String like= likes[0];
               likes[0] = String.valueOf(Integer.valueOf(likes[0])+1);
             //  holder.setData(text,resource,heart, likes[0]);
               holder.l.setText(likes[0]+" likes");
               ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
               query.whereEqualTo("username",text);
            //   query.whereEqualTo("likes",likes[0]);
               Log.i("info", String.valueOf(resource));
               query.findInBackground(new FindCallback<ParseObject>() {
                   @Override
                   public void done(List<ParseObject> objects, ParseException e) {
                       for(ParseObject object : objects){
                           Log.i("hello","hello");
                           object.put("likes",String.valueOf(Integer.valueOf(likes[0])));
                           object.saveInBackground(new SaveCallback() {
                               @Override
                               public void done(ParseException e) {
                                   if(e==null){
                                       Log.i("info","liked");

                                   }else{
                                       e.printStackTrace();
                                   }
                               }
                           });

                       }

                   }
               });






           }
       });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textt;
        private ImageView image;
        private ImageView heart;
        private TextView l;
        private ImageView profile;
        private ImageView speak;
        private ImageView saved;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textt  = itemView.findViewById(R.id.textView20);
            image = itemView.findViewById(R.id.imageView7);
            heart= itemView.findViewById(R.id.imageView11);
            l= itemView.findViewById(R.id.textView3);
            profile=itemView.findViewById(R.id.imageView8);
            speak=itemView.findViewById(R.id.imageView12);
            saved=itemView.findViewById(R.id.imageView13);

        }

        public void setData(String text, Bitmap resource, int i,String likes,Bitmap b) {
            image.setImageBitmap(resource);
            textt.setText(text);
            heart.setImageResource(i);
            l.setText(likes);
            profile.setImageBitmap(b);
        }
    }
}
