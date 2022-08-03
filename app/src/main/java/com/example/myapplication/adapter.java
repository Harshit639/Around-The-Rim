package com.example.myapplication;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

       holder.setData(text,resource);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textt;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textt  = itemView.findViewById(R.id.textView20);
            image = itemView.findViewById(R.id.imageView7);

        }

        public void setData(String text, Bitmap resource) {
            image.setImageBitmap(resource);
            textt.setText(text);
        }
    }
}
