package com.example.dsgvobestandsaufnahme;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    private ArrayList<Survey> surveys;
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyAdapter;

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
        surveyAdapter = new SurveyAdapter(getActivity(), surveys);
        surveyRecyclerView.setAdapter(surveyAdapter);
        initializeData();
        return rootView;
    }

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

}
