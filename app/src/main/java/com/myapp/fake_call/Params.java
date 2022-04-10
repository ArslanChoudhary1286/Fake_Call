package com.myapp.fake_call;

public class Params {
    int id;
    String name;
    String number;
    String imageUri;

    public Params() {
    }

    public Params(int id) {
        this.id = id;
    }

    public Params(String name) {
        this.name = name;
    }

    public Params(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Params(String number, String imageUri) {
        this.number = number;
        this.imageUri = imageUri;
    }

    public Params(int id, String name, String number, String imageUri) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
