package id.alin_gotama.mynoteapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbnoteapp";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE=
                    "CREATE TABLE " + DatabaseContract.TABLE_NOTE + " (" +
                            DatabaseContract.NoteColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            DatabaseContract.NoteColumns.TITLE + " TEXT NOT NULL, " +
                            DatabaseContract.NoteColumns.DESCRIPTION + " TEXT NOT NULL, " +
                            DatabaseContract.NoteColumns.DATE + " TEXT NOT NULL)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("anjay",SQL_CREATE_TABLE_NOTE);
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NOTE);
        this.onCreate(db);
    }
}
