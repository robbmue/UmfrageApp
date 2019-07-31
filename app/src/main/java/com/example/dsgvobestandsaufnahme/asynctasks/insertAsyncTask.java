package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;

import com.example.dsgvobestandsaufnahme.Survey;
import com.example.dsgvobestandsaufnahme.SurveyDao;

public class  insertAsyncTask extends AsyncTask<Survey, Void, Void> {

    private SurveyDao asyncTaskDao;

    public insertAsyncTask(SurveyDao dao){
        asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Survey... surveys) {
        asyncTaskDao.insert(surveys[0]);
        return null;
    }
}
