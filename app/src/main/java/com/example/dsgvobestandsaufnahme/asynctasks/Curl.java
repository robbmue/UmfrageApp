package com.example.dsgvobestandsaufnahme.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class Curl extends AsyncTask<URL, Integer, String> {

    private String output;
    public final String LOG_TAG = Curl.class.getSimpleName();

    @Override
    protected String doInBackground(URL... urls) {
        StringBuilder builder = new StringBuilder();

        for (URL url : urls) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    builder.append(line);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }


    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject json = new JSONObject(s);
            Log.d(LOG_TAG, json.getString("vendor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
