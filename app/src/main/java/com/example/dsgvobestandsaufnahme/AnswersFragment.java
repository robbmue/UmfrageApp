package com.example.dsgvobestandsaufnahme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsgvobestandsaufnahme.answers.AnswerAdapter;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.Survey;

class AnswersFragment extends Fragment {

    RecyclerView displayAnswers;
    Answers answers;
    AnswerAdapter answerAdapter;
    Survey survey;

    public AnswersFragment(Answers answers){
        this.answers = answers;
        this.survey = survey;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_answers, container, false);
        displayAnswers = v.findViewById(R.id.displayAnswers);
        displayAnswers.setLayoutManager(new LinearLayoutManager(getContext()));
        answerAdapter = new AnswerAdapter(answers);
        displayAnswers.setAdapter(answerAdapter);
        return v;
    }
}
