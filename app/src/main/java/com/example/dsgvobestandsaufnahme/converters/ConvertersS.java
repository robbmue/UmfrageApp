package com.example.dsgvobestandsaufnahme.converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.dsgvobestandsaufnahme.survey.Survey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ConvertersS {
    @TypeConverter
    public static Survey fromString(String value){
        Log.d(ConvertersQ.class.getSimpleName(), value);
        Type listType = new TypeToken<Survey>(){}.getType();
        Log.d(ConvertersQ.class.getSimpleName(), listType.toString());
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromSurvey(Survey survey){
        Gson gson = new Gson();
        String json = gson.toJson(survey);
        return json;
    }


}
