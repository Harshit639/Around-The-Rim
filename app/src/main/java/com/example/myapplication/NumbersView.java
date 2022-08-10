package com.example.myapplication;

import android.graphics.Bitmap;

public class NumbersView {
    private Bitmap ivNumbersImageId;
    private String mNumberInDigit;
    public NumbersView(Bitmap NumbersImageId, String NumbersInDigit) {
        ivNumbersImageId = NumbersImageId;
        mNumberInDigit = NumbersInDigit;

    }

    public Bitmap getNumbersImageId() {
        return ivNumbersImageId;
    }


    public String getNumberInDigit() {
        return mNumberInDigit;
    }


}
