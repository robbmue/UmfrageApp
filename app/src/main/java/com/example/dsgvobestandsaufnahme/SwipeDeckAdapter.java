package com.example.dsgvobestandsaufnahme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SwipeDeckAdapter extends BaseAdapter {

    private List<Question> questions;
    private Context context;

    public SwipeDeckAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // normally use a viewholder
            v = inflater.inflate(R.layout.question_card, parent, false);
        }
        ((TextView) v.findViewById(R.id.textView2)).setText(questions.get(position).getQuestion());
        ((TextView) v.findViewById(R.id.textView)).setText((questions.get(position).getId()+1) + "/" + questions.size());
        if (questions.get(position).isYn()) ((TextInputEditText) v.findViewById(R.id.input)).setVisibility(View.GONE);
        else ((TextInputEditText) v.findViewById(R.id.input)).setVisibility(View.VISIBLE);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getSimpleName() , questions.get(position).getQuestion());
            }
        });

        return v;
    }

}