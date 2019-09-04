package com.example.dsgvobestandsaufnahme;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.answers.AnswersAdapter;
import com.example.dsgvobestandsaufnahme.answers.AnswersViewModel;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {

    LayoutInflater inflater;
    RecyclerView displayOpen;
    List<Answers> answerList;
    AnswersAdapter answerAdapter;
    AnswersViewModel answersViewModel;



    public OpenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_open, container, false);

        displayOpen = v.findViewById(R.id.displayOpen);
        displayOpen.setLayoutManager(new LinearLayoutManager(getActivity()));
        answerList = new ArrayList<>();
        answerAdapter = new AnswersAdapter(getActivity());
        displayOpen.setAdapter(answerAdapter);
        answersViewModel = ViewModelProviders.of(this).get(AnswersViewModel.class);
        answerList = answersViewModel.getAllAnsweres().getValue();
        answersViewModel.getAllAnsweres().observe(this, new Observer<List<Answers>>() {
            @Override
            public void onChanged(List<Answers> answers) {
                answerAdapter.setAnswersData(answers);
            }
        });
        getAnswers();
        return v;
    }



    private void getAnswers() {
        class GetAnswers extends AsyncTask<Void, Void, List<Answers>> {

            @Override
            protected List<Answers> doInBackground(Void... voids) {
                List<Answers> answerList = SurveyRoomDatabase.getDatabase(getContext()).answerDao().getAll().getValue();
                return answerList;
            }

        }

        GetAnswers getAnswers = new GetAnswers();
        getAnswers.execute();
    }


}
