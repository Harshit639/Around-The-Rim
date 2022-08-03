package com.example.myapplication;

import android.graphics.Bitmap;

public class modelclass {
    private String textview1;
    private Bitmap imageview1;


    modelclass(String textview1, Bitmap imageview1 ){
        this.imageview1=imageview1;
        this.textview1=textview1;
    }

    public Bitmap getimageview1() {
        return imageview1;
    }

    public String gettextview1() {
        return textview1;
    }
}
