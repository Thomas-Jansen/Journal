package jansen.thomas.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =  "CREATE TABLE entries (_id integer PRIMARY KEY AUTOINCREMENT NOT NULL, title varchar(255)," +
                " content text, mood text, date datetime NOT NULL DEFAULT(GETDATE()));";
        sqLiteDatabase.execSQL(createTable);

        String insert = "INSERT INTO entries (title, content, mood, date) VALUES ('Eerste entry van mij', 'Lief dagboek', 'sad');";
        sqLiteDatabase.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE entries;";
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance == null) {
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
        }
        return  EntryDatabase.instance;
    }

    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase table = instance.getWritableDatabase();
        return table.rawQuery("SELECT * FROM entries", null);
    }

    public static void insert(EntryDatabase instance, JournalEntry entry) {
        SQLiteDatabase table = instance.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
//        values.put("date", entry.getTimestamp());
        table.insert("entries", null, values);
    }
}
