package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.FragmentSingleReservationBinding;
import com.ismagi.hotelreservation.models.Reservation;

public class SingleReservationFragment extends Fragment {
    String TAG = "SingleReservatinFragment";
    Reservation selectedReservation;
    FragmentSingleReservationBinding fsrb;

    public SingleReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Le viewBinding
        fsrb = FragmentSingleReservationBinding.inflate(inflater, container, false);


        if (getArguments() != null) {
            this.selectedReservation = SingleReservationFragmentArgs.fromBundle(getArguments()).getSelectedReservation();
            //La reservation selectionnée se trouve dans this.selectedReservation
            //Afficher tous ses details ici

            Log.i(TAG, "onCreateView: "+this.selectedReservation.getId());
        }
        else {

        }

        return fsrb.getRoot();
    }
}