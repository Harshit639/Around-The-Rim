package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class NumbersViewAdapter extends ArrayAdapter<NumbersView> {

    // invoke the suitable constructor of the ArrayAdapter class
    public NumbersViewAdapter(@NonNull Context context, ArrayList<NumbersView> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        NumbersView currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        ImageView numbersImage = currentItemView.findViewById(R.id.imageView);
        assert currentNumberPosition != null;
        numbersImage.setImageResource(currentNumberPosition.getNumbersImageId());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.textView1);
        textView1.setText(currentNumberPosition.getNumberInDigit());




        // then return the recyclable view
        return currentItemView;
    }
}
