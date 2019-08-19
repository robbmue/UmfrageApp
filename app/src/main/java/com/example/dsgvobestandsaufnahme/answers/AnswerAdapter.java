package com.example.dsgvobestandsaufnahme.answers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsgvobestandsaufnahme.R;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private Answers answers;


    public AnswerAdapter(Answers answers){
        this.answers = answers;
    }

    @NonNull
    @Override
    public AnswerAdapter.AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_text_view, parent, false);
        AnswerViewHolder vh = new AnswerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        List<Answer> answerList = answers.getAnswerArrayList();
        if (answerList != null) {
            holder.answerTextView.setText(answers.getSurvey().getQuestions().get(position).getQuestion() + " " + answerList.get(position).toString());
        }
    }

    @Override
    public int getItemCount() {
        if (answers.getAnswerArrayList() != null ) {
            return answers.getAnswerArrayList().size();
        }
        return 0;
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder{

        private TextView answerTextView;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
        }

    }
}
