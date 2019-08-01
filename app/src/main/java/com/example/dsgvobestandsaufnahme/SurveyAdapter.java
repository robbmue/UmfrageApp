package com.example.dsgvobestandsaufnahme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private List<Survey> surveyData;
    private Context context;
    private final LayoutInflater inflater;

    public SurveyAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new SurveyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyAdapter.ViewHolder holder, int position) {
        if (surveyData != null) {
            Survey currentSurvey = surveyData.get(position);
            holder.bindTo(currentSurvey);
        }else{
            return;
        }
    }

    void setSurveyData(List<Survey> surveys){
        surveyData = surveys;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
         if (surveyData != null) return surveyData.size();
         return 0;
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

        void bindTo(final Survey curretnSurvey){
            tvTitle.setText(curretnSurvey.getName());
            tvDescriptio.setText(curretnSurvey.getDescription());
            byte[] decodedString = Base64.decode(curretnSurvey.getImageResource(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivSurvey.setImageBitmap(decodedByte);
            ivSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestionFragment newFrag = new QuestionFragment(curretnSurvey);
                    MainActivity.openSurvey(newFrag);
                    /*
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.viewPager, newFrag)
                            .addToBackStack(null)
                            .commit();*/
                }
            });
        }
    }


}
