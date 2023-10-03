package com.example.faso_learn.data_base;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.faso_learn.models.User;


public class StorageHelper extends SQLiteOpenHelper {

    public StorageHelper(Context context) {
        super(context,"user_manager", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users (matricule INTEGER PRIMARY KEY AUTOINCREMENT,firstname TEXT NOT NULL,lastname TEXT NOT NULL,email TEXT NOT NULL UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("lastname",user.getLastname());
        cv.put("firstname",user.getFirstname());
        cv.put("email",user.getEmail());
        db.insert("users",null,cv);
        db.close();
    }

    public void updateUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("lastname",user.getLastname());
        cv.put("firstname",user.getFirstname());
        cv.put("email",user.getEmail());
        db.update("users",cv, "matricule=?",new String[]{String.valueOf(user.getMatricule())});
        db.close();
    }

    public void deleteUser(int matricule){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("users", "matricule=?",new String[]{String.valueOf(matricule)});
        db.close();
    }

    public Cursor getAllUser(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM users",null);
        return c;
    }

    public User getOneUser(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query("users",null,"email=?",new String[]{String.valueOf(email)},null,null,null);

        if(c.getCount() == 0)
            return  null;

        c.moveToFirst();
        return new User(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
    }
}


