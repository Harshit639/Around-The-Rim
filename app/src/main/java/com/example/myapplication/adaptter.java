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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class adaptter extends  RecyclerView.Adapter<adaptter.ViewHolder> {


    private ArrayList<frontpage> data;
    public static int posit;

    public adaptter(ArrayList<frontpage> data){

        this.data=data;
    }

    @NonNull
    @Override
    public adaptter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptter.ViewHolder holder, int position) {
        String title = data.get(position).getA();
        String lastt= data.get(position).getB();
        holder.setData(title,lastt);

        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("ProfileImage");
        query2.whereEqualTo("username",title);

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if( e==null){
                    if(objects.size()>0){
                        Log.i("ifo", String.valueOf(objects.size()));
                        for(ParseObject object : objects){
                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null) {
                                        Log.i("info","doing");
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        Bitmap b = Bitmap.createScaledBitmap(bitmap, 360,428, true);
                                        holder.c.setImageBitmap(b);
                                    }
                                }
                            });

                            break;
                        }
                    }else{
                        //  holder.setData(text,resource,heart, likes[0]+" likes",resource);
                        holder.c.setImageResource(R.drawable.asd);
                    }
                }
            }});


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a,b;
        private ImageView c;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c=itemView.findViewById(R.id.imageView15);
            a=itemView.findViewById(R.id.textView8);
            b=itemView.findViewById(R.id.lastmessage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent net = new Intent(v.getContext(), chatactivity.class);
                    posit= getAdapterPosition(); net.putExtra("nameofselected",data.get(posit).getA());
                    Log.i("info",data.get(posit).getA());
                    v.getContext().startActivity(net);
                }
            });

        }
        public void setData(String desc,String last) {

            a.setText(desc);
            b.setText(last);

        }
    }
}
