package com.example.phonebookapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(@Nullable Context context) {
            super(context, "PhoneBookDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table Contact_Book (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact Text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertData(String name, String contact)
    {
        String query="insert into Contact_Book (name,contact) values('"+name+"','"+contact+"') ";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
    public Cursor viewData()
    {
        String query="select* from Contact_Book";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        //db.execSQL(query);
        return cursor;
    }

    public void deleteData(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String qry = "delete from Contact_Book where id = '"+id+"'";
        sqLiteDatabase.execSQL(qry);
    }

    public void updateData(int id, String name, String contact) {

        String qry = "update Contact_Book set name = '"+name+"',contact='"+contact+"' where id = '"+id+"'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(qry);

    }
}
