package com.example.hwanpenndemo1.beans;

/**
 * Created by fangzhipeng on 2017/4/17.
 */
public class Book {
    private long id;
    private String name;
    private double price;

    public Book(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
