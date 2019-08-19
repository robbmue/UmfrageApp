package com.example.dsgvobestandsaufnahme.answers;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.util.List;

public class AnswersRepo {

    private AnswerDao answerDao;
    private LiveData<List<Answers>> allAnswers;

    public AnswersRepo(Application application){
        SurveyRoomDatabase db = SurveyRoomDatabase.getDatabase(application);
        answerDao = db.answerDao();
        allAnswers = (answerDao).getAll();
    }

    public LiveData<List<Answers>> getAllAnswers(){
        return allAnswers;
    }



}
