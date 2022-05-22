package com.ismagi.hotelreservation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ismagi.hotelreservation.DAO.IReservationDAO;
import com.ismagi.hotelreservation.DAO.ReservationDAO;
import com.ismagi.hotelreservation.MenuActivity;
import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.FragmentSingleReservationBinding;
import com.ismagi.hotelreservation.models.Reservation;

public class SingleReservationFragment extends Fragment implements View.OnClickListener {
    String TAG = "SingleReservatinFragment";
    Reservation selectedReservation;
    IReservationDAO dao;
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
        dao = new ReservationDAO(getContext());


        if (getArguments() != null) {

            fsrb.btnBackRes.setOnClickListener(this);
            fsrb.btnModifRes.setOnClickListener(this);
            fsrb.btnSupprRes.setOnClickListener(this);
            this.selectedReservation = SingleReservationFragmentArgs.fromBundle(getArguments()).getSelectedReservation();
            //La reservation selectionnée se trouve dans this.selectedReservation
            //Afficher tous ses details ici

            Log.i(TAG, "onCreateView: "+this.selectedReservation.getChambre().getNumero());

            fsrb.detIdreservation.setText(this.selectedReservation.getId());
            fsrb.detIdchambre.setText(this.selectedReservation.getIdChambre());
            fsrb.detIdclient.setText(this.selectedReservation.getIdUser());
            fsrb.detDateReservation.setText(this.selectedReservation.getDateReservation());
            fsrb.detDateEntree.setText(this.selectedReservation.getDateEntree());
            fsrb.detDateSortie.setText(this.selectedReservation.getDateSortie());
            fsrb.detMontant.setText(String.valueOf(this.selectedReservation.getMontant()));
            fsrb.detNbEnfants.setText(String.valueOf(this.selectedReservation.getNbEnfant()));
            fsrb.detNbAdults.setText(String.valueOf(this.selectedReservation.getNbAdult()));
            fsrb.dettypechambre.setText(this.selectedReservation.getChambre().getCategorie().getLibelle());


        }
        else {

        }

        return fsrb.getRoot();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_suppr_res:
            dao.Delete(this.selectedReservation.getId());
                Navigation.findNavController(view).navigate(R.id.action_singleReservationFragment_to_listReservationFragment);
                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
                Toast.makeText(getContext(), "Réservation "+this.selectedReservation.getId()+" supprimée.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_back_res:
                Navigation.findNavController(view).navigate(R.id.listReservationFragment);
                break;
            case R.id.btn_modif_res:
                break;

        }
    }
}