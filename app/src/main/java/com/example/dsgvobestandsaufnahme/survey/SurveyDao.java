package com.example.dsgvobestandsaufnahme.survey;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SurveyDao {

    @Insert
    void insert(Survey survey);

    @Query("DELETE FROM survey_table")
    void deleteAll();

    @Query("SELECT * from survey_table")
    LiveData<List<Survey>> getAllSurveys();

}
