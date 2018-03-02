package jansen.thomas.journal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Get dataBase, send it to EntryAdapter and use the adapter
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        ListView listview = findViewById(R.id.ListViewMain);
        listview.setAdapter(adapter);

//      Several onCLickListeners
        listview.setOnItemClickListener(new ListItemClickListener());
        listview.setOnItemLongClickListener(new ListOnItemLongClickListener());
        FloatingActionButton AddButton = findViewById(R.id.floatingActionButtonAdd);
        AddButton.setOnClickListener(new addButtonOnClickListener());
    }

//  Update the listView on resume
    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

//  When add button is pressed, go to Input
    public class addButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intentInput);
        }
    }

//  When an item is clicked, send its information to DetailActivity
    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String mood = cursor.getString(cursor.getColumnIndexOrThrow("mood"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
            intentDetail.putExtra("title", title);
            intentDetail.putExtra("mood", mood);
            intentDetail.putExtra("content", content);
            intentDetail.putExtra("date", date);
            startActivity(intentDetail);
        }
    }

//  When an item is long pressed, open a dialogbox and ask for confirmation to delete the item
    public class ListOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            final long deleteID = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));

//          AlertDialog method found on https://stackoverflow.com/questions/23195208/how-to-pop-up-
//                                 a-dialog-to-confirm-delete-when-user-long-press-on-the-list-item
            AlertDialog.Builder alertDelete = new AlertDialog.Builder(MainActivity.this);
            alertDelete.setMessage("Are you sure you want to delete this entry?");
            alertDelete.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                EntryDatabase.deleteEntry(deleteID);
                updateData();
                dialog.dismiss();
                }
            });
            alertDelete.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDelete.show();
            return true;
        }
    }

//  A function to update the listView
    private void updateData() {
        Cursor newCursor = EntryDatabase.selectAll(db);
        adapter.swapCursor(newCursor);
    }

}
