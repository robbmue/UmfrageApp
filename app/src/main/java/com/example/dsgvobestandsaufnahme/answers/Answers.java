package com.example.dsgvobestandsaufnahme.answers;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.dsgvobestandsaufnahme.survey.Survey;

import java.util.ArrayList;

@Entity(tableName = "answers")
public class Answers {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "companyName")
    private String companyName;


    @ColumnInfo(name = "survey")
    private Survey survey;

    @ColumnInfo(name = "answerArray")
    private ArrayList<Answer> answerArrayList;

    public Answers() {
    }

    public Answers(Survey survey, String companyName, int size) {
        this.survey = survey;
        this.companyName = companyName;
        this.answerArrayList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            answerArrayList.add(new Answer());
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public void setAnswerArrayList(ArrayList<Answer> answerArrayList) {
        this.answerArrayList = answerArrayList;
    }
}
