package com.example.dsgvobestandsaufnahme.survey;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SurveyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Survey survey);

    @Query("DELETE FROM survey_table")
    void deleteAll();

    @Query("SELECT * FROM survey_table WHERE name = :name")
    Survey getSurvey(String name);

    @Query("SELECT * from survey_table")
    LiveData<List<Survey>> getAllSurveys();

}
