package com.example.faso_learn.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.faso_learn.R;
import com.example.faso_learn.models.User;

import java.util.ArrayList;

// cette classe permet d'avoir une liste view d'objet User
public class CustomerAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<User> users;

    public CustomerAdapter(Context context, ArrayList<User> users) {
        super(context,0,users);
        this.context = context;
        this.users = users;
    }
    public void insertUserOnList(ArrayList<User> list){
        this.users = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        OwnView ownView;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.user_card,viewGroup,false);
            ownView = new OwnView(convertView);
            convertView.setTag(ownView);
        }else{
            ownView = (OwnView) convertView.getTag();
        }
        User list = users.get(position);
        ownView.nom.setText(list.getLastname());
        ownView.prenom.setText(list.getFirstname());
        ownView.email.setText(list.getEmail());
        ownView.matricule.setText(list.getMatricule().toString());

        return convertView;
    }


    static class OwnView{
        private final TextView nom;
        private final TextView prenom;
        private final TextView email;
        private final TextView matricule;

        OwnView(View view) {
            this.nom = view.findViewById(R.id.nom_card);
            this.prenom = view.findViewById(R.id.prenom_card);
            this.email = view.findViewById(R.id.email_card);
            this.matricule = view.findViewById(R.id.matricule);

        }
    }
}
