package com.example.faso_learn.controllers;

import android.content.Context;
import android.database.Cursor;

import com.example.faso_learn.data_base.StorageHelper;
import com.example.faso_learn.models.User;



import java.util.ArrayList;


public class AllUserController {

    public static ArrayList<User> getAllUser(Context context){

        ArrayList<User> users = new ArrayList<User>();
        StorageHelper storageHelper = new StorageHelper(context);
        Cursor cursor = storageHelper.getAllUser();
        int length = cursor.getCount();

        if (cursor.moveToFirst()) {
            System.out.println("dans Alluser");
            for (int i = 0; i < length; i++) {
                users.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                System.out.println(cursor.getInt(0));
                System.out.println(cursor.getString(1));
                cursor.moveToNext();
            }
        }

        return users;
    }


}
