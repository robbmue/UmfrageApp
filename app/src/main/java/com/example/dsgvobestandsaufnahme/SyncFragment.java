package com.example.dsgvobestandsaufnahme;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dsgvobestandsaufnahme.asynctasks.Curl;
import com.example.dsgvobestandsaufnahme.helper.CSVWriter;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;


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
                isStoragePermissionGranted();
            }
        });
        return rootView;
    }

    private void sync(View view) {


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

    private void exportCSV(View view){

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        Log.d(LOG_TAG, "Export Dir: " + exportDir.toString());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "answers" + ".csv");
        Log.d(LOG_TAG, "File: " + file.toString());
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = SurveyRoomDatabase.getDatabase(view.getContext()).query("SELECT * FROM " + "answers", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = new String[curCSV.getColumnCount()];
                for (int i = 0; i < curCSV.getColumnCount() - 1; i++)
                    arrStr[i] = curCSV.getString(i);
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Log.d(LOG_TAG, "Answers exported to answers.csv");
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }

    public  boolean isStoragePermissionGranted() {
        Log.d(LOG_TAG, "Check if permissions are granted");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(LOG_TAG, "Build Number is higher than marshmellow");
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(LOG_TAG,"Permission is granted");
                exportCSV(getView());
                return true;
            } else {

                Log.v(LOG_TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(LOG_TAG,"Permission is always granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d(LOG_TAG, "onRequestPermissionResults was reached");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(LOG_TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            exportCSV(getView());
        }
        return;
    }


}
