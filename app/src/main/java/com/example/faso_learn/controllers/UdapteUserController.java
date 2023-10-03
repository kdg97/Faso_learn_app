package com.example.faso_learn.controllers;

import android.content.Context;

import com.example.faso_learn.data_base.StorageHelper;
import com.example.faso_learn.models.User;


public class UdapteUserController extends AddUserController {

    private Integer matricule;
    private String lastname;
    private String firstname;
    private String email;

    public UdapteUserController(String lastname, String firstname, String email,int matricule) {
        super(lastname, firstname, email);
        this.matricule = matricule;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public void updateUser(Context context){
        StorageHelper storageHelper = new StorageHelper(context);
        storageHelper.updateUser(new User(matricule,firstname,lastname,email));
    }
}
