package jansen.thomas.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    String title = "";
    String content = "";
    String mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        EditText newTitle = findViewById(R.id.editTextTitleNew);
        EditText newContent = findViewById(R.id.editTextContentNew);
        EditText newMood = findViewById(R.id.editTextMood);
        Button addButton = findViewById(R.id.buttonSubmit);

        newContent.setMovementMethod(null);

        newTitle.addTextChangedListener(new TitleChangedListener());
        newContent.addTextChangedListener(new ContentChangedListener());
        newMood.addTextChangedListener(new MoodChangedListener());
        addButton.setOnClickListener(new ButtonOnclickListener());
    }

    public class TitleChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            title ="" + charSequence;
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    public class ContentChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            content = "" + charSequence;
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    public class MoodChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mood = "" + charSequence;
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    public class ButtonOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date today = Calendar.getInstance().getTime();
            String date = df.format(today);
            JournalEntry newEntry = new JournalEntry(0, title, content, mood, date);
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            EntryDatabase.insert(db, newEntry);
            Intent intentInput = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intentInput);
        }
    }
}
