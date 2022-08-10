package com.example.myapplication;

import android.graphics.Bitmap;

public class modelclass {
    private String textview1;
    private Bitmap imageview1;
    private int heart;
    private String likes;




    modelclass(String textview1, Bitmap imageview1, int heart, String likes ){
        this.imageview1=imageview1;
        this.textview1=textview1;
        this.heart=heart;
        this.likes=likes;
    }

    public Bitmap getimageview1() {
        return imageview1;
    }

    public String gettextview1() {
        return textview1;
    }
    public String getlikes() {
        return likes;
    }

    public int getheart() {
        return heart;
    }
}
