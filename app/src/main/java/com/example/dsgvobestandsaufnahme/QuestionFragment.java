package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.asynctasks.SafeAnswers;
import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private Survey survey;
    private Answers answers;
    private SwipeDeck cardStack;
    private FloatingActionButton fabYes;
    private FloatingActionButton fabNo;
    private FloatingActionButton fabBACK;
    private FloatingActionButton fabSAVE;
    private EditText notes;
    private int previousposition;
    public static final String LOG_TAG = QuestionFragment.class.getSimpleName();

    public QuestionFragment(Survey survey, String companyName) {
        this.survey = survey;
        this.answers = new Answers(survey , companyName, survey.getQuestions().size());
        Log.d(getClass().getSimpleName(), survey.getName() + "opened");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        cardStack = rootView.findViewById(R.id.swipe_deck);
        fabYes = rootView.findViewById(R.id.fabYES);
        fabNo = rootView.findViewById(R.id.fabNO);
        fabBACK = rootView.findViewById(R.id.fabBACK);
        fabSAVE = rootView.findViewById(R.id.fabSAVE);
        notes = rootView.findViewById(R.id.notes);


        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(survey.getQuestions(), getActivity().getApplicationContext());
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                previousposition = position;
                if (survey.getQuestions().get(position).isYn()) {
                    answers.getAnswerArrayList().set(position, new Answer(position, "NO", notes.getText().toString()));
                } else {
                    EditText input = (rootView.findViewById(R.id.input));
                    answers.getAnswerArrayList().set(position, new Answer(position, input.getText().toString(), notes.getText().toString()));
                }
                notes.setText("");
                Log.d(LOG_TAG, "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                previousposition = position;
                if (survey.getQuestions().get(position).isYn()) {
                    answers.getAnswerArrayList().set(position, new Answer(position, "YES", notes.getText().toString()));
                }else{
                    EditText input = ((EditText)rootView.findViewById(R.id.input));
                    answers.getAnswerArrayList().set(position, new Answer(position, input.getText().toString(), notes.getText().toString()));
                }
                notes.setText("");
                Log.d(LOG_TAG, "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.d(LOG_TAG, "no more cards");
            }

            @Override
            public void cardActionDown() {
                Log.d(LOG_TAG, "card swiped down");
            }

            @Override
            public void cardActionUp() {
                Log.d(LOG_TAG, "card swiped up");
            }
        });


        fabNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardLeft(400);
            }
        });

        fabYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardRight(400);
            }
        });

        fabBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.setSelection(previousposition);
                if (previousposition != 0) previousposition--;
            }
        });

        fabSAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SafeAnswers(SurveyRoomDatabase.getDatabase(getContext()),answers).execute();
            }
        });


        return rootView;
    }

}
