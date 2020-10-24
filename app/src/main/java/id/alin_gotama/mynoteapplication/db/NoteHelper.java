package id.alin_gotama.mynoteapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import id.alin_gotama.mynoteapplication.entity.Note;


public class NoteHelper {
    private static String DATABASE_TABLE = DatabaseContract.TABLE_NOTE;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    public NoteHelper(Context context) {
        this.context = context;
    }

    public NoteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(this.context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<Note> query() {
        ArrayList<Note> notes = new ArrayList<Note>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, DatabaseContract.NoteColumns._ID + " DESC", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE)));
                note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE)));
                notes.add(note);
                cursor.moveToNext();
                Log.d("anjay", note.getTitle() + note.getDate() + note.getDescription());

            } while (!cursor.isAfterLast());

            cursor.close();

        }
        return notes;
    }

    public Long insert(Note note){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE,note.getTitle());
        values.put(DatabaseContract.NoteColumns.DESCRIPTION,note.getDescription());
        values.put(DatabaseContract.NoteColumns.DATE,note.getDate());

        return database.insert(DatabaseContract.TABLE_NOTE,null,values);
    }

    public int update(Note note){

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NoteColumns.TITLE,note.getTitle());
        values.put(DatabaseContract.NoteColumns.DESCRIPTION,note.getDescription());
        values.put(DatabaseContract.NoteColumns.DATE,note.getDate());

        return database.update(DatabaseContract.TABLE_NOTE,values,DatabaseContract.NoteColumns._ID + " = " + note.getId(),null);
    }

    public int delete(int id){
        return database.delete(DatabaseContract.TABLE_NOTE,DatabaseContract.NoteColumns._ID + " = " + id,null);
    }

}
