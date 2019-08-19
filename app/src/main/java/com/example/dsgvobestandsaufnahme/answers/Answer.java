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
        if (notes.equals("")) return "Antwort: " + answer;
        return "Antwort: " + answer + ", notes: " + notes;
    }
}
