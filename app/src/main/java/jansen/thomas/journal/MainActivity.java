package jansen.thomas.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton AddButton = findViewById(R.id.floatingActionButtonAdd);
        AddButton.setOnClickListener(new addButtonOnClickListener());
        ListView.setOnItemClickListener(new ListItemClickListener());
        ListView.setOnItemLongClickListener(new ListOnItemLongClickListener());

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
    }

    public class addButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
        }
    }

    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    public class ListOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            return true;
        }
    }

}
