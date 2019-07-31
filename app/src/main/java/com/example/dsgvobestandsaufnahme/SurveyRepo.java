package com.example.dsgvobestandsaufnahme;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SurveyRepo {

    private SurveyDao surveyDao;
    private LiveData<List<Survey>> allSurveys;

    SurveyRepo(Application application){
        SurveyRoomDatabase db = SurveyRoomDatabase.getDatabase(application);
        surveyDao = db.surveyDao();
        allSurveys = surveyDao.getAllSurveys();
    }

    LiveData<List<Survey>> getAllSurveys(){
        return allSurveys;
    }

    public void insert(Survey survey){
        new insertAsyncTask(surveyDao).execute(survey);
    }

    private static class  insertAsyncTask extends AsyncTask<Survey, Void, Void>{

        private SurveyDao asyncTaskDao;

        insertAsyncTask(SurveyDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Survey... surveys) {
            asyncTaskDao.insert(surveys[0]);
            return null;
        }
    }
}
