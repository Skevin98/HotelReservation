package com.ismagi.hotelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ismagi.hotelreservation.DAO.ChambreDAO;
import com.ismagi.hotelreservation.DAO.IChambreDao;
import com.ismagi.hotelreservation.DAO.IReservationDAO;
import com.ismagi.hotelreservation.DAO.ReservationDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.databinding.ActivityCreateReservationBinding;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;
import com.ismagi.hotelreservation.models.Categorie;
import com.ismagi.hotelreservation.models.Chambre;
import com.ismagi.hotelreservation.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class CreateReservationActivity extends AppCompatActivity {

    ActivityCreateReservationBinding acrb;
    String TAG = "CreateReservation";
    private String catId;
    IChambreDao DAOChambre;
    IReservationDAO DAOReservation;
    List<Chambre> chambresByCat;
    Reservation r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding pour le layout
        acrb = ActivityCreateReservationBinding.inflate(getLayoutInflater());
        setContentView(acrb.getRoot());

        catId = getIntent().getStringExtra("idCategorie");
        Log.i(TAG, "onCreate: "+catId);

        chambresByCat = new ArrayList<>();

        DAOChambre = new ChambreDAO(this);
        DAOChambre.GetChambreByCategorie(catId, new VolleyCallback<List<Chambre>>() {

            @Override
            public void onSuccess(List<Chambre> result) {
                chambresByCat = result;
                //Charger cette liste de chambre dans une option de choix du layout ici
                Log.i(TAG, "onSuccess: "+chambresByCat.size());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //Afficher un message d'erreur si aucune categorie n'est trouvé
            }
        });

        //recuperer les informations de la reservation dans R et les attribuer à la reservation R

        DAOReservation = new ReservationDAO(this);

        //Creer la reservation
        //DAOReservation.Add(r);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}