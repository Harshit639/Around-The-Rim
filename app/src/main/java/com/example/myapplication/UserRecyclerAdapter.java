package com.example.myapplication;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{
    ArrayList<Bitmap> arrayList;

    public UserRecyclerAdapter(ArrayList<Bitmap> arrayList){
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.userphotositem,parent,false);
        Log.i("info,","Docvdne");
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder holder, int position) {
        Bitmap b = arrayList.get(position);
        holder.image.setImageBitmap(b);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                image = itemView.findViewById(R.id.imageView17);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
