package com.ismagi.hotelreservation.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ismagi.hotelreservation.ConnexionActivity;
import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.FragmentPreferencesBinding;

public class PreferencesFragment extends Fragment implements View.OnClickListener {

    FragmentPreferencesBinding fpb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fpb =  FragmentPreferencesBinding.inflate(inflater, container, false);


        fpb.layProfil.setOnClickListener(this);
        fpb.layDisco.setOnClickListener(this);
        fpb.layInfos.setOnClickListener(this);

        return fpb.getRoot();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public void onClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Deconnexion");
        alertDialogBuilder.setMessage("se deconnecter ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseAuth.getInstance().signOut();
                Intent activit = new Intent(getContext(), ConnexionActivity.class);
                getActivity().getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activit);
                getActivity().finish();
            }
        });
        alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        switch (view.getId()){
            case R.id.lay_profil:
                Navigation.findNavController(view).navigate(R.id.action_preferencesFragment_to_profilFragment);

            break;
            case R.id.lay_disco:

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            break;
            case R.id.lay_infos:
            Toast.makeText(getContext(), "Application de fin de module", Toast.LENGTH_LONG).show();
            break;
            default:
            break;
        }
    }
}