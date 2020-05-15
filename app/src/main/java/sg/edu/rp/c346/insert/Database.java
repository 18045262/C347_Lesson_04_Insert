package sg.edu.rp.c346.insert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "note.db";

    private static final String TABLE_TASK = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOTECONTENT = "noteContent";
    private static final String COLUMN_STARS = "stars";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTECONTENT + " TEXT,"
                + COLUMN_STARS + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);

    }

    public void insertTask(String noteContent, String stars){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTECONTENT,noteContent );
        values.put(COLUMN_STARS, stars);
        db.insert(TABLE_TASK, null, values);
        db.close();
    }

    public ArrayList<String> getAllNotes() {
        ArrayList<String> tasks = new ArrayList<String>();
        // Select all the tasks' description
        String selectQuery = "SELECT " + COLUMN_NOTECONTENT
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                tasks.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }


    public ArrayList<Note> getNoteContent() {
        ArrayList<Note> notes = new ArrayList<Note>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NOTECONTENT + ", "
                + COLUMN_STARS
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String noteContent = cursor.getString(1);
                int stars = cursor.getInt(2);
                Note obj = new Note(id, noteContent , stars);
                notes.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
}
