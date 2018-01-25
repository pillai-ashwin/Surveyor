package bumet.cs683.ashwinpillai.surveyor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ashwinpillai on 11/8/17.
 */

public class AnswerAdapter extends ArrayAdapter<Answers> {

    public AnswerAdapter(Activity context, List<Answers> arrayList_answers) {

        super(context, 0, arrayList_answers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Answers object_answer = getItem(position);

        TextView timestampTextView = listItemView.findViewById(R.id.timestamp_textview);
        timestampTextView.setText(String.valueOf(new Date(object_answer.getTimestamp())));

        TextView questionTextView = listItemView.findViewById(R.id.question_textview);
        questionTextView.setText(object_answer.getQuestion());

        TextView answerTextView = listItemView.findViewById(R.id.answer_textview);
        answerTextView.setText(object_answer.getAnswer());

        return listItemView;
    }
}
