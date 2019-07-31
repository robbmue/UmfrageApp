package com.example.dsgvobestandsaufnahme;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dsgvobestandsaufnahme.asynctasks.PopulateDbAsync;

@Database(entities = {Survey.class}, version = 2, exportSchema = false)
public abstract class SurveyRoomDatabase extends RoomDatabase {

    public abstract SurveyDao surveyDao();
    private static SurveyRoomDatabase INSTANCE;

    public static SurveyRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (SurveyRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SurveyRoomDatabase.class, "survey_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

}
