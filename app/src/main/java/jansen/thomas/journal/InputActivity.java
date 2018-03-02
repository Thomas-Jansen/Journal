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

//  Variables to save
    String title = "";
    String content = "";
    String mood = "";
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

//      Find the views
        EditText newTitle = findViewById(R.id.editTextTitleNew);
        EditText newContent = findViewById(R.id.editTextContentNew);
        EditText newMood = findViewById(R.id.editTextMood);
        Button addButton = findViewById(R.id.buttonSubmit);

//      Prevent the content textView from scrolling horizontally
        newContent.setMovementMethod(null);

//      Set onChangeListeners on textViews
        newTitle.addTextChangedListener(new TitleChangedListener());
        newContent.addTextChangedListener(new ContentChangedListener());
        newMood.addTextChangedListener(new MoodChangedListener());
        addButton.setOnClickListener(new ButtonOnclickListener());
    }

//  onChangeListeners:
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

//  When submit button is pressed, send als received texts
    public class ButtonOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

//          Get current date and time and format it to a String
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date today = Calendar.getInstance().getTime();
            date = df.format(today);
            JournalEntry newEntry = new JournalEntry(0, title, content, mood, date);
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            EntryDatabase.insert(db, newEntry);

//          Return to main after submitting data
            Intent intentInput = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intentInput);
        }
    }
}
