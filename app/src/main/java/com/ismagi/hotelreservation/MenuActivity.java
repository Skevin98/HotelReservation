package com.ismagi.hotelreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
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

        String id = getIntent().getStringExtra("idUser");
        Log.i(TAG, "onCreate: "+id);
    }
}