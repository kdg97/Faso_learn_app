package com.example.faso_learn.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faso_learn.R;
import com.example.faso_learn.controllers.AllUserController;
import com.example.faso_learn.models.User;

import java.util.ArrayList;

public class AllUserView extends AppCompatActivity  {

    private ListView listView;
    private ArrayList<User> users;
    private SearchView searchView;
    CustomerAdapter customerAdapter;

    private TextView no_all_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_view);
        getSupportActionBar().setTitle("Faso Learn");
        // activer la bar de tache
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        no_all_user = (TextView) findViewById(R.id.no_all_user);
        no_all_user.setText("");
        listView = (ListView) findViewById(R.id.list);
        searchView = (SearchView) findViewById(R.id.search);

         users = AllUserController.getAllUser(getApplicationContext());

         if(users.size() > 0){
             no_all_user.setText("");
             customerAdapter = new CustomerAdapter(AllUserView.this,users);
             this.listView.setAdapter(customerAdapter);
         }else {
             no_all_user.setText("Aucun utilisateur pour le moment");
         }



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterText(s);
                return true;
            }
        });

    }

    private void filterText(String s) {
        ArrayList<User> listFiltered = new ArrayList<User>();
        for(User user: users){
            if(user.getEmail().toLowerCase().contains(s.toLowerCase())){
                listFiltered.add(user);
            }
        }

        if(listFiltered.isEmpty()){
            Toast.makeText(this,"Ne correspond à aucun utilisateur",Toast.LENGTH_LONG).show();
        }else{
            customerAdapter.insertUserOnList(listFiltered);
        }
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