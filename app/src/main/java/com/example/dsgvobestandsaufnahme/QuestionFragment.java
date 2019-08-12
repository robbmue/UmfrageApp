package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.survey.Survey;
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
    private int previousposition;
    public static final String LOG_TAG = QuestionFragment.class.getSimpleName();

    public QuestionFragment(Survey survey, String companyName) {
        this.survey = survey;
        this.answers = new Answers(survey.getName(), companyName);
        Log.d(getClass().getSimpleName(), survey.getName() + "opened");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        cardStack = rootView.findViewById(R.id.swipe_deck);
        fabYes = rootView.findViewById(R.id.fabYES);
        fabNo = rootView.findViewById(R.id.fabNO);
        fabBACK = rootView.findViewById(R.id.fabBACK);

        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(survey.getQuestions(), getActivity().getApplicationContext());
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                previousposition = position;
                Log.d(LOG_TAG, "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                previousposition = position;
                Log.d(LOG_TAG ,"card was swiped right, position in adapter: " + position);
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


        fabNo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardLeft(400);
            }
        });

        fabYes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardRight(400);
            }
        });

        fabBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.setSelection(previousposition);
                if (previousposition!=0)previousposition--;
            }
        });


        return rootView;
    }

}
