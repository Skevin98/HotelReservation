package com.ismagi.hotelreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.Models.User;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    IDao<User> DAO;

    List<User> users = new ArrayList<>();
    User u = new User();

    ActivityMenuBinding amd;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amd = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(amd.getRoot());

        navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(amd.BottomNavigationView,navController);
        NavigationUI.setupActionBarWithNavController(this,navController);

        DAO = new PersonneDAO(this);

        //String id = getIntent().getStringExtra("idUser");
        //String id = "38d89168-4677-47b2-bf61-cfeb53410671";


        //GetById exemple
        /*DAO.GetById(id, new VolleyCallback<User>() {
            @Override
            public void onSuccess(User result) {
                u = result;
                Log.i(TAG, "onCreate: Firebase id de l'user "+u.getFirebaseId());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "Erreur :"+e);
            }
        });*/




        //GetAll exemple
        /*DAO.GetAll(new VolleyCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                users = result;
                Log.i(TAG, "onCreate: List des users "+ users.get(0).getMail());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "Erreur :"+e);
            }
        });*/


    }
}