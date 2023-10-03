package com.example.faso_learn.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.example.faso_learn.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton add;
    private ImageButton all;
    private ImageButton update;
    private ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        this.add = (ImageButton) findViewById(R.id.update_button);
        this.all = (ImageButton) findViewById(R.id.all);
        this.delete = (ImageButton) findViewById(R.id.delete);
        this.update = (ImageButton) findViewById(R.id.update);

        ArrayList<ImageButton> list_button = new ArrayList<ImageButton>();
        list_button.add(this.add);
        list_button.add(this.all);
        list_button.add(this.delete);
        list_button.add(this.update);

        ArrayList<Class> list_view = new ArrayList<Class>();
        list_view.add(AddUserView.class);
        list_view.add(AllUserView.class);
        list_view.add(DeleteUserView.class);
        list_view.add(UpdateUserView.class);


            this.all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Intent permet de demarrer des actions
                    // context recupere les element de l'appli
                    Intent intent = new Intent(getApplicationContext(),AllUserView.class);
                    //intent.putExtra("nom",et_nom.getText().toString());
                    startActivity(intent);
                    //finish();
                }
            });

            this.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Intent permet de demarrer des actions
                    // context recupere les element de l'appli
                    Intent intent = new Intent(getApplicationContext(),AddUserView.class);
                    //intent.putExtra("nom",et_nom.getText().toString());
                    startActivity(intent);
                    //finish();
                }
            });

        this.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent permet de demarrer des actions
                // context recupere les element de l'appli
                Intent intent = new Intent(getApplicationContext(),DeleteUserView.class);
                //intent.putExtra("nom",et_nom.getText().toString());
                startActivity(intent);
                //finish();
            }
        });

        this.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent permet de demarrer des actions
                // context recupere les element de l'appli
                Intent intent = new Intent(getApplicationContext(),UpdateUserView.class);
                //intent.putExtra("nom",et_nom.getText().toString());
                startActivity(intent);
                //finish();
            }
        });


    }
}