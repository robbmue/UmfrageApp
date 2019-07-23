package com.example.dsgvobestandsaufnahme;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

        URL romsCouch = null;
        try {
            romsCouch = new URL("http://10.10.6.201");
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "URL nicht korrekt formatiert.");
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(romsCouch.openStream(), "UTF-8")) {
        }) {
            for (String line; (line = reader.readLine())!=null;){
                Log.d(LOG_TAG, line);
            }
        } catch (UnsupportedEncodingException e) {
            Log.d(LOG_TAG, "Unsopported Encoding");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IO Exception");
            e.printStackTrace();
        }

    }
}
