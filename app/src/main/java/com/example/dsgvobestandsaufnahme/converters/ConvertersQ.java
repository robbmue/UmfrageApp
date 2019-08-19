package com.example.dsgvobestandsaufnahme.converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.dsgvobestandsaufnahme.survey.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConvertersQ {
    @TypeConverter
    public static ArrayList<Question> fromString(String value){
        Log.d(ConvertersQ.class.getSimpleName(), value);
        Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
        Log.d(ConvertersQ.class.getSimpleName(), listType.toString());
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Question> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


}
