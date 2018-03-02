package jansen.thomas.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

//  When this activity is loaded, fill all textViews with the corresponding text from intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String mood = intent.getStringExtra("mood");
        String content = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");
        TextView titleView = findViewById(R.id.textViewTitleDetail);
        TextView dateView = findViewById(R.id.textViewDateDetail);
        TextView moodView = findViewById(R.id.textViewMoodDetail);
        TextView contentView = findViewById(R.id.textViewContentDetail);

        titleView.setText(title);
        contentView.setText(content);
        moodView.setText(mood);
        dateView.setText(date);

//      Set textView for content scrollable
        contentView.setMovementMethod(new ScrollingMovementMethod());
    }
}
