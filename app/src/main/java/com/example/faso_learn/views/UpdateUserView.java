package com.example.faso_learn.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faso_learn.R;
import com.example.faso_learn.controllers.AllUserController;
import com.example.faso_learn.models.User;

import java.util.ArrayList;


public class UpdateUserView extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<User> users;
    private SearchView searchView;
    private TextView no_update_user;
    CustomerAdapter customerAdapter;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_view);
        getSupportActionBar().setTitle("Faso Learn");
        // activer la bar de tache
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        no_update_user = (TextView) findViewById(R.id.no_update_user);
        no_update_user.setText("");
        listView = (ListView) findViewById(R.id.list_up);
        searchView = (SearchView) findViewById(R.id.search_up);

        users = AllUserController.getAllUser(getApplicationContext());

        if(users.size() > 0){
            no_update_user.setText("");
            // instancier  un CustomerAdapter
            customerAdapter = new CustomerAdapter(UpdateUserView.this,users);

            // liaison ListView au CustomerAdapter
            this.listView.setAdapter(customerAdapter);
            listView.setOnItemClickListener(this);
        }else {
            no_update_user.setText("Aucun utilisateur pour le moment");
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
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        int matricule = users.get(position).getMatricule();
        String firstname = users.get(position).getFirstname();
        String lastname = users.get(position).getLastname();
        String email = users.get(position).getEmail();

        User user = new User(matricule,firstname,lastname,email);

        Intent intent = new Intent(UpdateUserView.this, UpdateViewBis.class);
        intent.putExtra("user",user); // faut implementer serializable au modele user pour le faire
        this.startActivity(intent);
        this.finish();
    }
}