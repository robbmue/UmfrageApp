package com.example.dsgvobestandsaufnahme.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.Survey;
import com.example.dsgvobestandsaufnahme.SurveyDao;
import com.example.dsgvobestandsaufnahme.SurveyRoomDatabase;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final SurveyDao dao;
    private Context context;
    Survey[] surveys;

    public PopulateDbAsync(Context context, SurveyRoomDatabase db, Survey[] surveys) {
        this.dao = db.surveyDao();
        this.context = context;
        this.surveys = surveys;
    }

    public PopulateDbAsync(Context context, SurveyRoomDatabase db) {
        this.dao = db.surveyDao();
        this.context = context;
    }

    public PopulateDbAsync(SurveyRoomDatabase db) {
        this.dao = db.surveyDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.deleteAll();
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