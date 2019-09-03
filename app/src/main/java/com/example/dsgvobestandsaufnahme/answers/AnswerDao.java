package com.example.dsgvobestandsaufnahme.answers;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnswerDao {

    @Query("SELECT * FROM answers")
    LiveData<List<Answers>> getAll();

    @Query("SELECT COUNT(*) FROM answers")
    int countAnswers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Answers answers);

    @Delete
    void delete(Answers answers);

}
