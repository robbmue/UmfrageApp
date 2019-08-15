package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;

import com.example.dsgvobestandsaufnahme.answers.AnswerDao;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

public class SafeAnswers extends AsyncTask<Void,Void,Void> {

    private AnswerDao dao;
    private Answers answers;

    public SafeAnswers(SurveyRoomDatabase db, Answers answers){
        dao = db.answerDao();
        this.answers = answers;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.insertAll(answers);
        return null;
    }
}
