package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


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


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a,b;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
