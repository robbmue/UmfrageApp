package com.example.dsgvobestandsaufnahme.answers;

public class Answer {

    private int id;
    private String answer;
    private String notes;


    public Answer(int id, String answer, String notes) {
        this.id = id;
        this.answer = answer;
        this.notes = notes;
    }

    public Answer(){};

    @Override
    public String toString() {
        return "ID: " + id + ", answer: " + answer + ", notes: " + notes;
    }
}
