package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;

import com.example.dsgvobestandsaufnahme.Survey;
import com.example.dsgvobestandsaufnahme.SurveyDao;
import com.example.dsgvobestandsaufnahme.SurveyRoomDatabase;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final SurveyDao dao;
    Survey[] surveys = {new Survey("Title","Description",0)};

    public PopulateDbAsync(SurveyRoomDatabase db) {
        this.dao = db.surveyDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.deleteAll();
        for (Survey survey:
                surveys) {
            dao.insert(survey);

        }
        return null;
    }
}