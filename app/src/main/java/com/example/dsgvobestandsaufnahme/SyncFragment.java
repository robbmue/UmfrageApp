package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SyncFragment extends Fragment {

    private static final String LOG_TAG = SyncFragment.class.getSimpleName();


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
        URL url = null;
        try {
            url = new URL("http://10.10.6.208:5984");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new Curl().execute(url);
    }
}
