package com.example.android.projectmap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "user_table";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private static final String DATABASE_NAME = "USER_DATABASE";
    private static final int DATABASE_VERSION = 1;

    public LoginDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME + "(email TEXT,password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean addValue(String _email,String _password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL,_email);
        values.put(PASSWORD,_password);
        long r = db.insert(TABLE_NAME,null,values);
        if (r==-1)
            return false;
        else
            return true;
    }

    Cursor getValue(String _email,String _password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email='" + _email + "' AND password='" + _password + "'",null);
        return c;
    }

    Cursor getAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return c;
    }



    boolean deleteValue(String _email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, EMAIL + "=" + "'" + _email + "'", null) > 0;

    }
}
