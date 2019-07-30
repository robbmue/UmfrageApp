package com.example.dsgvobestandsaufnahme;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class Curl extends AsyncTask<URL, Integer, String> {


    @Override
    protected String doInBackground(URL... urls) {
        StringBuilder builder = new StringBuilder();

        for (URL url:urls) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    builder.append(line);
                    Log.d(Curl.class.getSimpleName(), line);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }
}
