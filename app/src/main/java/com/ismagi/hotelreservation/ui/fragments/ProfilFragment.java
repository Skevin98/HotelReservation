package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.Models.User;
import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.ActivityMenuBinding;
import com.ismagi.hotelreservation.databinding.FragmentProfilBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfilFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfilFragment";
    private FragmentProfilBinding fpb;

    IDao<User> DAO;

    User u = new User();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fpb = FragmentProfilBinding.inflate(inflater, container, false);
        fpb.propos.setOnClickListener(this);
        DAO = new PersonneDAO(getContext());
        String id = "38d89168-4677-47b2-bf61-cfeb53410671";

        //User temp = new User();
        //temp.setId("66666666-5717-4562-b3fc-2c963f66afa6");
//        temp.setFirebaseId("Fonctionne");
//        temp.setMail("Kevin@gmail.com");
//        temp.setAge(100);
//        temp.setPrenom("Android");


        //Update user exemple
        //DAO.Update(temp,"66666666-5717-4562-b3fc-2c963f66afa6");

        /*DAO.GetById("66666666-5717-4562-b3fc-2c963f66afa6", new VolleyCallback<User>() {
            @Override
            public void onSuccess(User result) {
                Log.i(TAG, "onSuccess: update " + result.getMail());
            }

            @Override
            public void onError(String e) {

            }
        });*/

        //Delete user exemple
        //DAO.Delete("66666666-5717-4562-b3fc-2c963f66afa6");


        //GetById exemple
        DAO.GetById(id, new VolleyCallback<User>() {
            @Override
            public void onSuccess(User result) {
                u = result;
                Log.i(TAG, "OnSuccess: Firebase id de l'user "+u.getFirebaseId());

                fpb.txtPNom.setText(u.getNom());
                fpb.txtPPrenom.setText(u.getPrenom());
                fpb.txtPUsername.setText(u.getUsername());
                fpb.txtPTel.setText(u.getNumero());
                fpb.txtPMail.setText(u.getMail());
                fpb.txtPAdresse.setText(u.getAdresse());
                fpb.txtPSexe.setText(u.getSexe());
                fpb.txtPYear.setText(String.valueOf(u.getAge()));
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "Erreur :"+e);
            }
        });


        return fpb.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.propos:
                Toast.makeText(getContext(), "Application de fin de module", Toast.LENGTH_LONG).show();
                break;
            case R.id.txt_p_username:
                openDialogUSer();
                break;
            default:
                break;
        }
    }


    private void openDialogUSer() {

    }
}