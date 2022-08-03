package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<proceed> arrayList;

    public RecycleAdapter(ArrayList<proceed> arrayList){
        this.arrayList=arrayList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        Log.i("info,","Docvdne");
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        proceed p = arrayList.get(position);

        holder.text.setText(p.getTitle());
        holder.image.setImageResource(p.getPostImage());
       // holder.image.setImageBitmap(p.getPostImage());
        Log.i("info,","Docvdne");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                image = itemView.findViewById(R.id.imageView6);
                text = itemView.findViewById(R.id.textView13);
                Log.i("info,","Done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
