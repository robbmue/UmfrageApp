package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.answers.AnswerDao;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

public class SafeAnswers extends AsyncTask<Answers,Void,Void> {

    private AnswerDao dao;
    private Answers answers;

    public SafeAnswers(SurveyRoomDatabase db){
        dao = db.answerDao();
    }

    @Override
    protected Void doInBackground(Answers... answers) {
        dao.insertAll(answers);
        for (Answers obj :
                answers) {
            Log.d(getClass().getSimpleName(), "Safe answers " + obj.getSurvey().getName() + " for " + obj.getCompanyName());

        }
        return null;
    }
}
