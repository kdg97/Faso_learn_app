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
import com.example.faso_learn.controllers.UdapteUserController;
import com.example.faso_learn.models.User;

public class UpdateViewBis extends AppCompatActivity {

    private EditText lastname;
    private EditText firstname;
    private EditText email;
    private TextView response;
    private int matricule;
    private Button add;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_view_bis);


        getSupportActionBar().setTitle("Faso Learn");
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

        // recuperation des données envoyé par update
        if(getIntent().getExtras() != null) {

            user = (User) getIntent().getSerializableExtra("user");
            lastname.setText(user.getLastname());
            firstname.setText(user.getFirstname());
            email.setText(user.getEmail());
            matricule = user.getMatricule();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = lastname.getText().toString();
                String prenom = firstname.getText().toString();
                String mail = email.getText().toString();
                System.out.println(nom);
                System.out.println(prenom);
                System.out.println(email);
                UdapteUserController updateUserController = new UdapteUserController(nom,prenom,mail,matricule);
                if(!updateUserController.checkAllFieldsNotEmpty()){
                    response.setTextColor(Color.RED);
                    response.setText("Remplissez tous les champs du formulaire");
                }else{
                    if(!updateUserController.checkEmailIsValid()){
                        response.setTextColor(Color.RED);
                        response.setText("Adresse mail invalide");
                        return;
                    }

                        updateUserController.updateUser(getApplicationContext());
                        response.setText("");
                        Toast.makeText(getApplicationContext(),"Utilisateur modifié !!!", Toast.LENGTH_LONG).show();



                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}