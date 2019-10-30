package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.Question;
import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.util.List;

public class ExportCSV extends AsyncTask<Void,Void,Void> {

    private SurveyRoomDatabase db;
    private final String LOG_TAG = "Fuck log tags!";
    private List<Answers> answersList;

    public ExportCSV(SurveyRoomDatabase db) {
        this.db = db;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        answersList = db.answerDao().getAll().getValue();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d(LOG_TAG, "reached post execute");
        if (answersList == null) {
            for (Answers answers :
                    answersList) {
                Survey survey = answers.getSurvey();
                List<Question> questionList = survey.getQuestions();
                List<Answer> answerList = answers.getAnswerArrayList();
                for (int i = 0; i < questionList.size(); i++) {
                    Log.d(LOG_TAG, questionList.get(i).toString() + answerList.toString());
                }
            }
        }else{
            Log.d(LOG_TAG, "answers seem to be null");
        }
    }
}

