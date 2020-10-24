package id.alin_gotama.mynoteapplication.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_NOTE = "note";

    public static class NoteColumns implements BaseColumns{
        public static final String _ID = "id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
    }

}
