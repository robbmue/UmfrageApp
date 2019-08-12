package com.example.dsgvobestandsaufnahme.answers;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "answers")
public class Answers {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "companyName")
    private String companyName;

    @ColumnInfo(name = "survey")
    private String surveyName;

    @ColumnInfo(name = "answerArray")
    private ArrayList<Answer> answerArrayList;

    public Answers() {
    }

    public Answers(String survey, String companyName) {
        this.surveyName = survey;
        this.companyName = companyName;
        this.answerArrayList = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Answer> getAnswerArrayList() {
        return answerArrayList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerArrayList(ArrayList<Answer> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }
}
