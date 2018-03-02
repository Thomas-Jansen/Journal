package jansen.thomas.journal;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

// An adapter to take the entry_row template, fill the textViews and send it to main.
public class EntryAdapter extends ResourceCursorAdapter{

    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String mood = cursor.getString(cursor.getColumnIndexOrThrow("mood"));

        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView dateView = view.findViewById(R.id.textViewDate);
        TextView moodView = view.findViewById(R.id.Mood);

        titleView.setText(title);
        dateView.setText(date);
        moodView.setText(mood);
    }
}

