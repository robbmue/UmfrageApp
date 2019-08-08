package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.dsgvobestandsaufnahme.asynctasks.Curl;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SyncFragment extends Fragment {

    private static final String LOG_TAG = SyncFragment.class.getSimpleName();
    public static final int GANDALF_YOU_SHALL_NOT_PASS_thisvalue = 10;

    public SyncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sync, container, false);
        Button syncButton = rootView.findViewById(R.id.button_sync);
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sync(view);
            }
        });
        return rootView;
    }

    public void sync(View view) {


        URL[] urls = new URL[GANDALF_YOU_SHALL_NOT_PASS_thisvalue];

        try {
            for (int i = 0; i < GANDALF_YOU_SHALL_NOT_PASS_thisvalue; i++) {
                urls[i]=(new URL("http://10.10.6.208:5984/surveys/" + i));
            }
            new Curl(getActivity().getApplicationContext()).execute(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


}
