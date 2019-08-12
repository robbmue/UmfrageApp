package com.example.dsgvobestandsaufnahme.survey;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    @ColumnInfo(name = "questions")
    private ArrayList<Question> questions;


    public Survey(String name, String description, String imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public Survey(String title, String description, String pic, JSONArray questions) throws JSONException {
        this.name = title;
        this.description = description;
        this.imageResource = pic;
        this.questions = new ArrayList<>();
        for (int i = 0; i < questions.length(); i++) {
            JSONObject obj = questions.getJSONObject(i);
            this.questions.add(new Question(obj.getInt("id"), obj.getBoolean("yn"), obj.getString("question")));
            Log.d(getClass().getSimpleName(), questions.get(i).toString());
        }
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
