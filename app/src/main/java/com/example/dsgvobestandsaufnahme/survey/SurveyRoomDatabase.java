package com.example.dsgvobestandsaufnahme.survey;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dsgvobestandsaufnahme.ConvertersA;
import com.example.dsgvobestandsaufnahme.ConvertersQ;
import com.example.dsgvobestandsaufnahme.answers.AnswerDao;
import com.example.dsgvobestandsaufnahme.answers.Answers;
import com.example.dsgvobestandsaufnahme.asynctasks.PopulateDbAsync;

@Database(entities = {Survey.class, Answers.class}, version = 4, exportSchema = false)
@TypeConverters({ConvertersQ.class, ConvertersA.class})
public abstract class SurveyRoomDatabase extends RoomDatabase {

    public abstract SurveyDao surveyDao();
    public abstract AnswerDao answerDao();
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

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

}
