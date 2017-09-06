package com.example.android.projectmap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DistenceDatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "distence_table";
    public static final String SOURCE = "source";
    public static final String DESTINATION = "destination";
    public static final String DISTENCE = "distence";

    private static final String DATABASE_NAME = "DISTENCE_DATABASE";
    private static final int DATABASE_VERSION = 1;

    public DistenceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME + "(source TEXT,destination TEXT,distence REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean addValue(String _source,String _destination,Double _distence)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SOURCE,_source);
        values.put(DESTINATION,_destination);
        values.put(DISTENCE,_destination);

        long r = db.insert(TABLE_NAME,null,values);
        if (r==-1)
            return false;
        else
            return true;
    }

    Cursor getAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return c;
    }



    boolean deleteValue(String _source)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, SOURCE + "=" + "'" + _source + "'", null) > 0;

    }
}
