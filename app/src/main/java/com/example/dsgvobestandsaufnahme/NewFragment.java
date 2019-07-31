package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private ArrayList<Survey> surveys;
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
        surveyViewModel.getAllSurveys().observe(this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> surveys) {
                surveyAdapter.setSurveyData(surveys);
            }
        });
        //initializeData();
        return rootView;
    }

    /*
    private void initializeData() {
        // Get the resources from the XML file.
        String [] surveyList = getResources().getStringArray(R.array.survey_titles);
        String [] surveyDescriptions = getResources().getStringArray(R.array.survey_description);
        TypedArray surveyImages = getResources().obtainTypedArray(R.array.survey_images);

        // Clear the existing data (to avoid duplication).
        surveys.clear();

        for (int i = 0; i < surveyList.length; i++) {
            surveys.add(new Survey(surveyList[i], surveyDescriptions[i], surveyImages.getResourceId(i,0)));
        }

        surveyImages.recycle();

        surveyAdapter.notifyDataSetChanged();
    }
    */

}
