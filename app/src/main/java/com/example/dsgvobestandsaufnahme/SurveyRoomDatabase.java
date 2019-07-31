package com.example.dsgvobestandsaufnahme;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Survey.class}, version = 1, exportSchema = false)
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




    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final SurveyDao dao;
        Survey[] surveys = {new Survey("Title","Description",0)};

        private PopulateDbAsync(SurveyRoomDatabase db) {
            this.dao = db.surveyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            for (Survey survey:
                 surveys) {
                dao.insert(survey);

            }
            return null;
        }
    }
}
