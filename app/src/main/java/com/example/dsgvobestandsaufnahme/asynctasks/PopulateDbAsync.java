package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.answers.AnswerDao;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyDao;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final SurveyDao surveyDao;
    private final AnswerDao answerDao;
    Survey[] surveys;
    Answers[] answers;


    public PopulateDbAsync(SurveyRoomDatabase db) {
        this.surveyDao = db.surveyDao();
        this.answerDao = db.answerDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //dao.deleteAll();
        if (surveys != null) {
            Log.d(this.getClass().getSimpleName(), "Populate Surveys");
            for (Survey survey :
                    surveys) {
                surveyDao.insert(survey);
            }
            answerDao.insertAll(answers);
        }
        return null;
    }
}