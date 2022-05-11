package com.ismagi.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismagi.hotelreservation.DAO.CategorieDAO;
import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.IReservationDAO;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.DAO.ReservationDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.models.Categorie;
import com.ismagi.hotelreservation.models.Reservation;
import com.ismagi.hotelreservation.models.User;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    IDao<User> DAO;
    IDao<Categorie> DAOCat;
    IReservationDAO DAORes;

    //List<User> users = new ArrayList<>();
    User u = new User();
    List<Categorie> categories = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    FirebaseDatabase mData;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

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

        mData = FirebaseDatabase.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = mData.getReference("Users");

        reference.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object user = snapshot.getValue();
                if (user==null) {
                    throw new AssertionError("Object cannot be null");
                }
                else
                {
                    HashMap<String, String> hashMap;
                    hashMap = (HashMap<String, String>) user;
                    DAO = new PersonneDAO(getApplicationContext());
                    DAO.GetById(hashMap.get("idAPI"), new VolleyCallback<User>() {
                        @Override
                        public void onSuccess(User result) {
                            u = result;

                            Log.i(TAG, "onCreate: Firebase id de l'user "+u.getFirebaseId());


                            DAORes = new ReservationDAO(getApplicationContext());

                            DAORes.GetReservationByUser(u.getId(), new VolleyCallback<List<Reservation>>() {

                                @Override
                                public void onSuccess(List<Reservation> result) {
                                    reservations = result;
                                    Log.i(TAG, "DAO res: "+reservations.size());
                                }

                                @Override
                                public void onError(String e) {

                                }
                            });
                        }

                        @Override
                        public void onError(String e) {
                            Log.i(TAG, "Erreur :"+e);
                        }
                    });


                    //Log.i(TAG, "accès à la base " +hashMap.get("idAPI")+" // "+hashMap.get("Mail"));


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        DAO = new PersonneDAO(this);

        DAOCat = new CategorieDAO(this);
        DAOCat.GetAll(new VolleyCallback<List<Categorie>>() {
            @Override
            public void onSuccess(List<Categorie> result) {
                categories = result;
                Log.i(TAG, "DAO Cat: "+categories.size());
            }

            @Override
            public void onError(String e) {

            }
        });




        //String id = "38d89168-4677-47b2-bf61-cfeb53410671";





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

    public User getUser(){
        return this.u;
    }

    public List<Categorie> GetCategories(){
        return this.categories;
    }


    public List<Reservation> GetReservations(){
        return this.reservations;
    }
}