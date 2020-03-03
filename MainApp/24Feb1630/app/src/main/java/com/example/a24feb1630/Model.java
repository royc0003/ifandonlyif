package com.example.a24feb1630;

public class Model
{
    String title;
    String desc;
    int icon;

    //constructor
    public Model(String title, String desc, int icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    //getters


    public String getDesc() {
        return this.desc;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }
}

