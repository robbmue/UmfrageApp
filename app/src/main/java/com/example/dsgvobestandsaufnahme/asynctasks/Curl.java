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
import java.util.LinkedList;

public class Curl extends AsyncTask<URL, Integer, String> {

    public final String LOG_TAG = Curl.class.getSimpleName();
    private Context context;

    public Curl(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(URL... urls) {
        StringBuilder builder = new StringBuilder();
        Log.d(LOG_TAG, "---------------------------------------------------------------------------------CURL gestartet");
        for (URL url : urls) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    builder.append(line);
                    builder.append("HIERTRENNEN");
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

        String[] array = s.split("HIERTRENNEN");
        try {

            LinkedList<JSONObject> jsons = new LinkedList<>();
            for (String obj : array
            ) {
                jsons.addLast(new JSONObject(obj));
            }

            LinkedList<Survey> surveys = new LinkedList<>();
            for (JSONObject obj :
                    jsons) {
                Log.d(LOG_TAG, obj.get("questions").toString());
                surveys.addLast(new Survey(obj.getString("title"), obj.getString("description"), obj.getString("pic"), obj.getJSONArray("questions")));
            }

            Log.d(LOG_TAG, "JSON is fucking awesome *-*");
            new SyncDbAsync(SurveyRoomDatabase.getDatabase(context), surveys.toArray(new Survey[surveys.size()])).execute();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "JSON sucks");
        }
    }
}
