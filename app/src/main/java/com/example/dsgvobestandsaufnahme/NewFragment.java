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

import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyAdapter;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;
import com.example.dsgvobestandsaufnahme.survey.SurveyViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private List<Survey> surveys;
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyAdapter;
    private SurveyViewModel surveyViewModel;

    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new,container,false);


        //Initialize the Survey-RV
        surveyRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        surveyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        surveys = new ArrayList<>();
        surveyAdapter = new SurveyAdapter(getActivity());
        surveyRecyclerView.setAdapter(surveyAdapter);
        surveyViewModel = ViewModelProviders.of(this).get(SurveyViewModel.class);
        surveys = surveyViewModel.getAllSurveys().getValue();
        surveyViewModel.getAllSurveys().observe(this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> surveys) {
                surveyAdapter.setSurveyData(surveys);
            }
        });
        getSurveys();
        return rootView;
    }

    private void getSurveys(){
        class GetSurveys extends AsyncTask<Void, Void, List<Survey>>{

            @Override
            protected List<Survey> doInBackground(Void... voids) {
                List<Survey> surveyList = SurveyRoomDatabase.getDatabase(getContext()).surveyDao().getAllSurveys().getValue();
                return surveyList;
            }
        }

        GetSurveys gs = new GetSurveys();
        gs.execute();
    }


}
