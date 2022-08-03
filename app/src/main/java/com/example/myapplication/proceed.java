package com.example.myapplication;

public class proceed {
    String Title;
    int postImage;

    public proceed(String title, int postImage) {
        Title = title;
        this.postImage = postImage;
    }

    public  String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public  int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
}
