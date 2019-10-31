package com.example.dsgvobestandsaufnahme.answers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnswersViewModel extends AndroidViewModel {

    private AnswersRepo repo;
    private LiveData<List<Answers>> allAnsweres;

    public AnswersViewModel(@NonNull Application application) {
        super(application);
        repo = new AnswersRepo(application);
        allAnsweres = repo.getAllAnswers();
    }

    public LiveData<List<Answers>> getAllAnsweres(){
        if (allAnsweres == null){
            allAnsweres = repo.getAllAnswers();
        }
        return allAnsweres;
    }

}
