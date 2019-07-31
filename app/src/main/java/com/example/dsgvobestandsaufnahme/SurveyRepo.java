package com.example.dsgvobestandsaufnahme;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dsgvobestandsaufnahme.asynctasks.insertAsyncTask;

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

}
