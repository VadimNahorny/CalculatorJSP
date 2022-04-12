package com.example.auth.good;

public class Good {
    private String name;
    private String image;

    public Good(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
