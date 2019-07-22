package com.example.dsgvobestandsaufnahme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private ArrayList<Survey> surveyData;
    private Context context;

    public SurveyAdapter(Context context, ArrayList<Survey> surveyData){
        this.surveyData = surveyData;
        this.context = context;
    }

    @NonNull
    @Override
    public SurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyAdapter.ViewHolder holder, int position) {
        Survey currentSurvey = surveyData.get(position);
        holder.bindTo(currentSurvey);
    }

    @Override
    public int getItemCount() {
        return surveyData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvDescriptio;
        private ImageView ivSurvey;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title);
            tvDescriptio = itemView.findViewById(R.id.description);
            ivSurvey = itemView.findViewById(R.id.pic);

        }

        void bindTo(Survey curretnSurvey){
            tvTitle.setText(curretnSurvey.getName());
            tvDescriptio.setText(curretnSurvey.getDescription());
            Glide.with(context).load(curretnSurvey.getImageResource()).into(ivSurvey);
        }
    }


}
