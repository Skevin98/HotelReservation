package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.DAO.IReservationDAO;
import com.ismagi.hotelreservation.DAO.ReservationDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.MenuActivity;
import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.adapters.ReservationAdapter;
import com.ismagi.hotelreservation.models.Reservation;
import com.ismagi.hotelreservation.databinding.FragmentListReservationBinding;

import java.util.ArrayList;
import java.util.List;


public class ListReservationFragment extends Fragment implements View.OnClickListener {

    private String TAG = "ListReservationFragment";

    FragmentListReservationBinding frb;
    public ReservationAdapter adapter;

    IReservationDAO DAO;
    Reservation r = new Reservation();
    List<Reservation> reservations = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frb = FragmentListReservationBinding.inflate(inflater, container, false);
        frb.ResRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReservationAdapter(getContext());
        reservations = ((MenuActivity) getActivity()).GetReservations();

        adapter.setReservations(reservations);
        Log.i(TAG, "onCreateView: "+adapter.getItemCount());

        frb.setAdapter(adapter);





        //DAO = new ReservationDAO(getContext());
        /*Reservation temp = new Reservation();
        temp.setId("173dd347-5750-4295-a4f6-fa362ee28c80");
        temp.setIdUser("66666666-5717-4562-b3fc-2c963f66afa6");
        temp.setIdChambre("ffffffff-5555-4562-b3fc-2c963f66afa6");
        temp.setMontant(25000);*/


        //Add reservation exemple
        //DAO.Add(temp);


        //get by id exemple
        /*DAO.GetById(temp.getId(), new VolleyCallback<Reservation>() {
            @Override
            public void onSuccess(Reservation result) {
                Log.i(TAG, "onSuccess by ID: "+result.getDateReservation());
            }

            @Override
            public void onError(String e) {

            }
        });*/


        //get all exemple
//        DAO.GetAll(new VolleyCallback<List<Reservation>>() {
//            @Override
//            public void onSuccess(List<Reservation> result) {
//                Log.i(TAG, "onSuccess get all: "+result.size());
//            }
//
//            @Override
//            public void onError(String e) {
//                Log.i(TAG, "onError get all: "+e);
//            }
//        });

        //get by user exemple
/*        DAO.GetReservationByUser(temp.getIdUser(),new VolleyCallback<List<Reservation>>() {
            @Override
            public void onSuccess(List<Reservation> result) {
                Log.i(TAG, "onSuccess get by user: "+result.size());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError get by user: "+e);
            }
        });*/


        //Update exemple
        //DAO.Update(temp, temp.getId());


        //Delete exemple
        //DAO.Delete(temp.getId());

        frb.fBtnCreate.setOnClickListener(this);

        return frb.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.f_btn_create:
                Navigation.findNavController(view).navigate(R.id.action_listReservationFragment_to_categorieFragment);
        }

    }
}