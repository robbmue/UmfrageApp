package com.example.dsgvobestandsaufnahme;

public class Survey {

    private String name;
    private String description;
    private final int imageResource;


    public Survey(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
