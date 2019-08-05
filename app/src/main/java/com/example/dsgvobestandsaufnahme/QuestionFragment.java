package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.daprlabs.cardstack.SwipeDeck;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private Survey survey;
    private SwipeDeck cardStack;
    public static final String LOG_TAG = QuestionFragment.class.getSimpleName();

    public QuestionFragment(Survey survey) {
        this.survey = survey;
        Log.d(getClass().getSimpleName(), survey.getName() + "opened");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        cardStack = rootView.findViewById(R.id.swipe_deck);
        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(survey.getQuestions(), getActivity().getApplicationContext());
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.d(LOG_TAG, "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
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
        return rootView;
    }

}
