package com.example.dsgvobestandsaufnahme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.example.dsgvobestandsaufnahme.answers.AnswerAdapter;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.helper.CSVWriter;
import com.example.dsgvobestandsaufnahme.survey.Question;
import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.example.dsgvobestandsaufnahme.survey.SurveyRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AnswersFragment extends Fragment {

    RecyclerView displayAnswers;
    Answers answers;
    AnswerAdapter answerAdapter;
    FloatingActionButton exportButton;
    final String LOG_TAG = "EXPORT";

    public AnswersFragment(Answers answers) {
        this.answers = answers;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_answers, container, false);
        displayAnswers = v.findViewById(R.id.displayAnswers);
        displayAnswers.setLayoutManager(new LinearLayoutManager(getContext()));
        answerAdapter = new AnswerAdapter(answers);
        displayAnswers.setAdapter(answerAdapter);
        exportButton = v.findViewById(R.id.exportButton);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStoragePermissionGranted();
            }
        });
        return v;
    }

    private boolean isStoragePermissionGranted() {

        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Log.v(LOG_TAG, "Permission is granted");
            exportCSV();
            return true;
        } else {

            Log.v(LOG_TAG, "Permission is revoked");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d(LOG_TAG, "onRequestPermissionResults was reached");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(LOG_TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            exportCSV();
        }
        return;
    }


    private void exportCSV() {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        Log.d(LOG_TAG, "Export Dir: " + exportDir.toString());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }


        File file = new File(exportDir, "answers" + answers.getCompanyName() + ".csv");
        Log.d(LOG_TAG, "File: " + file.toString());

        List<Answer> ansList = answers.getAnswerArrayList();
        List<Question> questionList = answers.getSurvey().getQuestions();

        //TODO Colum names, company name, survey name


        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            String firstline[] = new String[1];
            firstline[0] = "Umfarge " + answers.getSurvey().getName() + " für " + answers.getCompanyName();
            csvWrite.writeNext( firstline );
            for (int i = 0; i < answers.getAnswerArrayList().size(); i++) {
                String arrStr[] = new String[3];
                arrStr[0] = questionList.get(i).getQuestion().toString();
                arrStr[1] = ansList.get(i).getAnswer();
                if (ansList.get(i).getNotes() != null) arrStr[2] = ansList.get(i).getNotes();
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();

            Log.d(LOG_TAG, "Answers exported to answers" + answers.getCompanyName() + ".csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
