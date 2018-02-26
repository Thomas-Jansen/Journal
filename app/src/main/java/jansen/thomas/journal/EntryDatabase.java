package jansen.thomas.journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =  "CREATE TABLE entries (id integer primary key, title varchar(255)," +
                " content text, mood text, date datetime DEFAULT(getdate()));";
        sqLiteDatabase.execSQL(createTable);

        String insert = "INSERT INTO entries (title, content, mood) VALUES ('Eerste entry', 'Lief dagboek', ':(');";
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


    public EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance == null) {
            EntryDatabase.instance = new EntryDatabase(context, getDatabaseName(), null, 1);
        }
        return  EntryDatabase.instance;
    }

    private Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase table = instance.getWritableDatabase();
        Cursor cursor = table.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }
}
