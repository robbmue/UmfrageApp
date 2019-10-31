package com.example.dsgvobestandsaufnahme;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.asynctasks.Curl;
import com.example.dsgvobestandsaufnahme.helper.CSVWriter;
import com.example.dsgvobestandsaufnahme.survey.Question;
import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SyncFragment extends Fragment {

    private static final String LOG_TAG = SyncFragment.class.getSimpleName();
    private static final int GANDALF_YOU_SHALL_NOT_PASS_thisvalue = 10;

    public SyncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_sync, container, false);
        Button syncButton = rootView.findViewById(R.id.button_sync);
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sync(view);
            }
        });
        Button exportButton = rootView.findViewById(R.id.button_export);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuckYourself();
            }

            private void fuckYourself() {
                Log.d(LOG_TAG, "BITCH YOU TOUGHT! NOTHING IS GONNA HAPPEN!");
            }
        });
        return rootView;
    }

    private void sync(View view) {


        URL[] urls = new URL[GANDALF_YOU_SHALL_NOT_PASS_thisvalue];

        try {
            for (int i = 0; i < GANDALF_YOU_SHALL_NOT_PASS_thisvalue; i++) {
                urls[i] = (new URL("http://10.10.6.208:5984/surveys/" + i));
            }
            new Curl(getActivity().getApplicationContext()).execute(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }





}
