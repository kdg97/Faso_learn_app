package com.example.faso_learn.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faso_learn.R;
import com.example.faso_learn.controllers.AddUserController;


public class AddUserView extends AppCompatActivity {

    private EditText lastname;
    private EditText firstname;
    private EditText email;
    private TextView response;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Faso Learn");
        setContentView(R.layout.activity_add_user_view);
        // appel action bar
        ActionBar actionBar = getSupportActionBar();

        // affiche le  boutton de retour dans action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        add = (Button) findViewById(R.id.update_button);
        response = (TextView) findViewById(R.id.res_up);
        email = (EditText) findViewById(R.id.email_up);
        response.setText("");
        lastname = (EditText) findViewById(R.id.nom_up);
        firstname = (EditText) findViewById(R.id.prenom_up);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = lastname.getText().toString();
                String prenom = firstname.getText().toString();
                String mail = email.getText().toString();
                System.out.println(nom);
                System.out.println(prenom);
                System.out.println(email);
                AddUserController addUserController = new AddUserController(nom,prenom,mail);
                if(!addUserController.checkAllFieldsNotEmpty()){
                    response.setTextColor(Color.RED);
                    response.setText("Remplissez tous les champs du formulaire");
                }else{
                    if(!addUserController.checkEmailIsValid()){
                        response.setTextColor(Color.RED);
                        response.setText("Adresse mail invalide");
                        return;
                    }
                    if(addUserController.checkEmailAlreadyExist(getApplicationContext(),mail)){
                        response.setTextColor(Color.RED);
                        response.setText("Désolé cette adresse mail est déja prise");
                    }else{
                        addUserController.addUser(getApplicationContext());
                        response.setText("");
                        Toast.makeText(getApplicationContext(),"Utilisateur enregistré !!!", Toast.LENGTH_LONG).show();
                    }


                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}