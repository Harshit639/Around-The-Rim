package com.example.myapplication;

public class NumbersView {
    private int ivNumbersImageId;
    private String mNumberInDigit;
    public NumbersView(int NumbersImageId, String NumbersInDigit) {
        ivNumbersImageId = NumbersImageId;
        mNumberInDigit = NumbersInDigit;

    }

    public int getNumbersImageId() {
        return ivNumbersImageId;
    }


    public String getNumberInDigit() {
        return mNumberInDigit;
    }


}
