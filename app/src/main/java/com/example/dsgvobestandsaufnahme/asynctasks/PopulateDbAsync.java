package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyDao;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final SurveyDao dao;
    Survey[] surveys;


    public PopulateDbAsync(SurveyRoomDatabase db) {
        this.dao = db.surveyDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //dao.deleteAll();
        if (surveys != null) {
            Log.d(this.getClass().getSimpleName(), surveys[0].getName());
            for (Survey survey :
                    surveys) {
                dao.insert(survey);

            }
        }
        return null;
    }
}