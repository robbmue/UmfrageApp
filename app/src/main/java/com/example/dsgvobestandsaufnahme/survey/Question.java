package com.example.dsgvobestandsaufnahme.survey;

public class Question {

    private int id;
    private boolean yn;
    private String question;

    public Question(int id, boolean yn, String question) {
        this.id = id;
        this.yn = yn;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isYn() {
        return yn;
    }

    public void setYn(boolean yn) {
        this.yn = yn;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
