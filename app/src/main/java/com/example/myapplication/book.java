package com.example.myapplication;

public class book {
   private String name;
    private String price;
    private String years;
    private String type;


    public book(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAge() {
        return years;
    }

    public void setAge(String age) {
        this.years = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public book(String name, String price, String years, String type) {
        this.name = name;
        this.price = price;
        this.years = years;
        this.type = type;
    }
}
