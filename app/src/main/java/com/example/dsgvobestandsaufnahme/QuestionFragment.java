package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private Survey survey;

    public QuestionFragment(Survey survey) {
        this.survey = survey;
        Log.d(getClass().getSimpleName(), survey.getName() + "opened");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        TextView test = rootView.findViewById(R.id.test);
        test.setText(survey.getName() + "\n" + survey.getDescription());
        return rootView;
    }

}
