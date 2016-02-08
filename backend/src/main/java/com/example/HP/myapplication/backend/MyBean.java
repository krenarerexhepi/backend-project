package com.example.HP.myapplication.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {



    public String getData() {
        return myData;
    }
    private String myData;

    public void setData(String data) {
        myData = data;
    }
}