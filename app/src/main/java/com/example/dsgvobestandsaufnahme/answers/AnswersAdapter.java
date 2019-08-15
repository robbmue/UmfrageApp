package com.example.dsgvobestandsaufnahme.answers;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsgvobestandsaufnahme.R;

import java.util.List;


public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Answers> answersData;
    private Context context;
    private final LayoutInflater inflater;

    public AnswersAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new AnswersAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswersAdapter.ViewHolder holder, int position) {
        if (answersData != null) {
            Answers currentAnswers = answersData.get(position);
            holder.bindTo(currentAnswers);
        }else{
            return;
        }
    }

    public void setAnswersData(List<Answers> surveys){
        answersData = surveys;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
         if (answersData != null) return answersData.size();
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

        void bindTo(final Answers curretnAnswers){
            tvTitle.setText(curretnAnswers.getSurveyName());
            tvDescriptio.setText(curretnAnswers.getCompanyName());
            /*byte[] decodedString = Base64.decode(curretnAnswers.getImageResource(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivSurvey.setImageBitmap(decodedByte);*/

        }
    }


}
