package com.example.dsgvobestandsaufnahme;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "survey_table")
public class Survey {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "imageResource")
    private String imageResource;

    /*@ColumnInfo(name = "questions")
    private String[] questions;*/


    public Survey(String name, String description, String imageResource) {
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

    public String getImageResource() {
        return imageResource;
    }


    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

}
