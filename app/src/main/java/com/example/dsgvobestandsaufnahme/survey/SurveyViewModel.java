package com.example.dsgvobestandsaufnahme.survey;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SurveyViewModel extends AndroidViewModel {

    private SurveyRepo repo;
    private LiveData<List<Survey>> allSurveys;

    public SurveyViewModel(@NonNull Application application) {
        super(application);
        repo = new SurveyRepo(application);
        allSurveys = repo.getAllSurveys();
    }

    public LiveData<List<Survey>> getAllSurveys(){
        return allSurveys;
    }

    public void insert(Survey survey){
        repo.insert(survey);
    }
}
