package com.cstructor.helloworld;

import java.util.ArrayList;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my.db";
    private static final int DB_VERSION = 1;
    private static DbHelper myDb = null;
    private SQLiteDatabase db;
    public static final String DB_SESSION_TABLE = "Session";
    private static final String CreateSessionTable = "create table "
            + DB_SESSION_TABLE
            + "(ID integer primary key autoincrement, Name text, StartDate integer); ";

    private DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        open();
    }

    public static DbHelper getInstance(Context context)
    {
        if (myDb == null)
        {
            myDb = new DbHelper(context);
        }

        return myDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateSessionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_SESSION_TABLE);
        onCreate(db);
    }

    private void open() throws SQLiteException
    {
        db = getWritableDatabase();
    }

    public long addSession(String name, Date date)
    {
        ContentValues values = new ContentValues();

        values.put("Name", name);
        values.put("StartDate", date.getTime());

        long rowId = db.insert(DB_SESSION_TABLE, null, values);
        return rowId;
    }

    public DBSession[] getSessions()
    {
        ArrayList<DBSession> sessions = new ArrayList<DBSession>();

        Cursor cursor = db.query(DB_SESSION_TABLE, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            while (true)
            {
                DBSession session = new DBSession();

                session.SessionId = cursor.getInt(cursor.getColumnIndex("ID"));

                session.Name = cursor.getString(cursor.getColumnIndex("Name"));

                session.StartDate = new Date(cursor.getLong(cursor.getColumnIndex("StartDate")));

                sessions.add(session);
                if (!cursor.moveToNext())
                {
                    break;
                }
            }
        }

        cursor.close();

        DBSession[] dbSessions = new DBSession[sessions.size()];

        sessions.toArray(dbSessions);

        return dbSessions;
    }

    public void updateSession(long id, String name, Date date)
    {
        ContentValues values = new ContentValues();

        values.put("Name", name);
        values.put("StartDate", date.getTime());

        db.update(DB_SESSION_TABLE, values, "ID=" + id, null);
    }

    public void deleteSession(long id)
    {
        db.delete(DB_SESSION_TABLE, "ID=" + id, null);
    }

    public void deleteSessions()
    {
        db.delete(DB_SESSION_TABLE, null, null);
    }
}
