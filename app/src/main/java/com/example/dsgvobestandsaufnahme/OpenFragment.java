package com.example.dsgvobestandsaufnahme;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.example.dsgvobestandsaufnahme.answers.AnswerDao;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {

    LayoutInflater inflater;
    TextView displayOpen;

    public OpenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_open, container, false);
    }

    public void refresh(){

        View v = getView();
        //v = inflater.inflate(R.layout.fragment_open, ((ViewGroup)v.getParent()),false);
        displayOpen = v.findViewById(R.id.testOpen);
        SurveyRoomDatabase db = SurveyRoomDatabase.getDatabase(getContext());
        AnswerDao dao = db.answerDao();
        getAnswers();

    }

    private void getAnswers(){
        class GetAnswers extends AsyncTask<Void, Void, List<Answers>> {

            @Override
            protected List<Answers> doInBackground(Void... voids) {
                List<Answers> answerList = SurveyRoomDatabase.getDatabase(getContext()).answerDao().getAll();
                return answerList;
            }

            @Override
            protected void onPostExecute(List<Answers> answers) {
                for (Answers obj:
                     answers) {
                    displayOpen.append(obj.getSurveyName() + " für " + obj.getCompanyName() + "\n");
                    for (Answer ans :
                            obj.getAnswerArrayList()) {
                        displayOpen.append(ans.toString() + "\n");
                    }
                }
            }
        }

        GetAnswers getAnswers = new GetAnswers();
        getAnswers.execute();
    }


}
