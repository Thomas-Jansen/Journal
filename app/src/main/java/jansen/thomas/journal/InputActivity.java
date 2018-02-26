package jansen.thomas.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent intent = getIntent();
        EditText newTitle = findViewById(R.id.editTextTitleNew);
        EditText newContent = findViewById(R.id.editTextContentNew);
        Button addButton = findViewById(R.id.buttonSubmit);

        newTitle.addTextChangedListener(new TextChangedListener());
        newContent.addTextChangedListener(new TextChangedListener());
        addButton.setOnClickListener(new ButtonOnclickListener());
    }

    public class TextChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    public class ButtonOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }
}
