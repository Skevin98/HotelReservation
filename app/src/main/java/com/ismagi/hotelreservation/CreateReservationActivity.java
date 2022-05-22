package com.ismagi.hotelreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismagi.hotelreservation.DAO.ChambreDAO;
import com.ismagi.hotelreservation.DAO.IChambreDao;
import com.ismagi.hotelreservation.DAO.IReservationDAO;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.DAO.ReservationDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.databinding.ActivityCreateReservationBinding;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;
import com.ismagi.hotelreservation.models.Categorie;
import com.ismagi.hotelreservation.models.Chambre;
import com.ismagi.hotelreservation.models.Reservation;
import com.ismagi.hotelreservation.models.User;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CreateReservationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCreateReservationBinding acrb;

    final Calendar myCalendar= Calendar.getInstance();

    String TAG = "CreateReservation";
    private String catId, userId;
    IChambreDao DAOChambre;
    IReservationDAO DAOReservation;
    List<Chambre> chambresByCat;
    Reservation r;


    FirebaseDatabase mData;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding pour le layout
        acrb = ActivityCreateReservationBinding.inflate(getLayoutInflater());
        setContentView(acrb.getRoot());


        acrb.pickerAdults.setMinValue(1);
        acrb.pickerAdults.setMaxValue(5);

        acrb.pickerEnf.setMinValue(0);
        acrb.pickerEnf.setMaxValue(5);

        r = new Reservation();

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

                    userId = hashMap.get("idAPI");

                    r.setIdUser(userId);

                    Log.i(TAG, "accès à la base " +hashMap.get("idAPI")+" // "+hashMap.get("Mail"));

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        DatePickerDialog.OnDateSetListener dateE =new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                /*view.setMinDate(System.currentTimeMillis() - 1000);
                if (!acrb.txtDateS.getText().toString().isEmpty()){
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yy");
                    Date date = null;
                    try {
                        date = format.parse(acrb.txtDateS.getText().toString());
                        long maxDate = date.getTime();
                        view.setMaxDate(maxDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }*/


                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                updateLabelE();
            }
        };
        
        DatePickerDialog.OnDateSetListener dateS =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                updateLabelS();
            }
        };

        acrb.txtDateE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateReservationActivity.this,dateE,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        acrb.txtDateS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateReservationActivity.this,dateS,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });






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

                ArrayAdapter<Chambre> adapter = new ArrayAdapter<Chambre>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, chambresByCat);



                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);



                acrb.spinnerChambre.setAdapter(adapter);
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //Afficher un message d'erreur si aucune categorie n'est trouvé
            }
        });
        



        acrb.spinnerChambre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onItemSelectedHandler(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        acrb.btnCRes.setOnClickListener(this);

        acrb.btnAnnRes.setOnClickListener(this);

    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int i, long l) {
        Adapter adapter = adapterView.getAdapter();
        Chambre ch = (Chambre) adapter.getItem(i);
        r.setIdChambre(ch.getId());

    }

    private void updateLabelE(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.ENGLISH);
        acrb.txtDateE.setText(dateFormat.format(myCalendar.getTime()));

    }

    private void updateLabelS(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.ENGLISH);
        acrb.txtDateS.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_c_res:
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.US);

                r.setDateEntree(acrb.txtDateE.getText().toString());
                r.setDateSortie(acrb.txtDateS.getText().toString());
                r.setNbAdult(acrb.pickerAdults.getValue());
                r.setNbEnfant(acrb.pickerEnf.getValue());
                r.setActive(true);
                r.setMontant(500.0);


                Log.i(TAG, "onClick: reservationid: " + r.getId());
                Log.i(TAG, "onClick: reservation id chambre: " + r.getIdChambre());
                Log.i(TAG, "onClick: reservation id user: " + r.getIdUser());



                DAOReservation = new ReservationDAO(this);
                DAOReservation.Add(r);

                break;
            case R.id.btn_ann_res:
                Log.i(TAG, "onClick Annuler: ");
                finish();
                break;
        }

    }
}