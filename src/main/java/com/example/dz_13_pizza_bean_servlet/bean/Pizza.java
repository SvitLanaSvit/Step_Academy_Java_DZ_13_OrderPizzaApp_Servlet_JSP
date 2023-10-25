package com.example.dz_13_pizza_bean_servlet.bean;

import java.io.Serializable;

public class Pizza implements Serializable {
    private int id;
    private String name;

    public Pizza(){}
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}