package com.example.faso_learn.controllers;

import android.content.Context;

import com.example.faso_learn.data_base.StorageHelper;
import com.example.faso_learn.models.User;



import java.util.regex.Pattern;

public class AddUserController {

    private String lastname;
    private String firstname;
    private String email;

    public AddUserController(String lastname, String firstname, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    public boolean checkAllFieldsNotEmpty(){
        return !lastname.trim().isEmpty() && !firstname.trim().isEmpty() && !email.trim().isEmpty();
    }

    public boolean checkEmailAlreadyExist(Context context,String email){
        User user = null;
        StorageHelper storageHelper = new StorageHelper(context);
        user = storageHelper.getOneUser(email);
        return user != null;
    }

    public boolean checkEmailIsValid(){
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            return Pattern.compile(regex)
                    .matcher(email)
                    .matches();
    }

    public void addUser(Context context){
        StorageHelper storageHelper = new StorageHelper(context);
        User user = new User(firstname,lastname,email);
        storageHelper.insertUser(user);
    }

}
