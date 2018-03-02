package jansen.thomas.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

//  Create a sql table called entries to store entries by id
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =  "CREATE TABLE entries (_id integer PRIMARY KEY AUTOINCREMENT NOT NULL, title varchar(255)," +
                " content text, mood text, date text);";
        sqLiteDatabase.execSQL(createTable);
    }

//  When a new version of the app is installed, delete the old table and create a new one
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE IF EXISTS entries;";
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

//  If the table doesn't yet exist, create a new one
    public static EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance == null) {
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
        }
        return  EntryDatabase.instance;
    }

//  Select all entries in the table and return it as a cursor
    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase table = instance.getWritableDatabase();
        return table.rawQuery("SELECT * FROM entries", null);
    }

//  Store a new entry in the table
    public static void insert(EntryDatabase instance, JournalEntry entry) {
        SQLiteDatabase table = instance.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
        values.put("date", entry.getTimestamp());
        table.insert("entries", null, values);
    }

//  Delete an entry by it's id
    public static void deleteEntry(long deleteID) {
        SQLiteDatabase table = instance.getWritableDatabase();
        table.delete("entries", "_id = ?", new String[] {String.valueOf(deleteID)});
    }
}
