package com.example.dsgvobestandsaufnahme.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dsgvobestandsaufnahme.Survey;
import com.example.dsgvobestandsaufnahme.SurveyRoomDatabase;

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
    private Context context;

    public Curl(Context context) {
        this.context = context;
    }

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
            Survey[] surveys = {new Survey(json.getString("title"), json.getString("description"),json.getString("pic"))};
            Log.d(LOG_TAG, "JSON is fucking awesome *-*");
            new PopulateDbAsync(context, SurveyRoomDatabase.getDatabase(context), surveys).execute();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "JSON sucks");
        }
    }
}
