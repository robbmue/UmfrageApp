package com.example.dsgvobestandsaufnahme;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.dsgvobestandsaufnahme.answers.Answer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConvertersA {
    @TypeConverter
    public static ArrayList<Answer> fromString(String value){
        Log.d(ConvertersQ.class.getSimpleName(), value);
        Type listType = new TypeToken<ArrayList<Answer>>(){}.getType();
        Log.d(ConvertersQ.class.getSimpleName(), listType.toString());
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Answer> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


}
