package com.example.faso_learn.controllers;

import android.content.Context;

import com.example.faso_learn.data_base.StorageHelper;


public class DeleteUserController {

    public static void deleteUser(Context context,int matricule){
        StorageHelper storageHelper = new StorageHelper(context);
        storageHelper.deleteUser(matricule);
    }
}
