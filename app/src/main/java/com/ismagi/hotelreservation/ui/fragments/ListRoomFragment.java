package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.DAO.CategorieDAO;
import com.ismagi.hotelreservation.DAO.ChambreDAO;
import com.ismagi.hotelreservation.DAO.IChambreDao;
import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.PersonneDAO;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.Models.Categorie;
import com.ismagi.hotelreservation.Models.Chambre;
import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.FragmentListRoomBinding;
import com.ismagi.hotelreservation.databinding.FragmentProfilBinding;

import java.util.ArrayList;
import java.util.List;


public class ListRoomFragment extends Fragment {

    private static final String TAG = "RoomFragment";
    List<Chambre> chambres = new ArrayList<>();
    List<Chambre> chambresByCat = new ArrayList<>();
    Chambre c = new Chambre();

    List<Categorie> categories = new ArrayList<>();
    Categorie cat = new Categorie();

    IChambreDao DAO;
    IDao<Categorie> DAOCat;
    private FragmentListRoomBinding frb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frb = FragmentListRoomBinding.inflate(inflater, container, false);

        //String id = "ffffffff-2222-4562-b3fc-2c963f66afa6";
        String idCat = "cccccccc-5717-4562-b3fc-2c963f66afa6";

        DAO = new ChambreDAO(getContext());

        DAOCat = new CategorieDAO(getContext());


        //get all categories
        DAOCat.GetAll(new VolleyCallback<List<Categorie>>() {
            @Override
            public void onSuccess(List<Categorie> result) {

                //Si la requete passe avec succès, les données seront accessibles dans result
                categories = result;
                Log.i(TAG, "Liste des categories: "+categories.size());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //En cas d'echec, prevoir l'affichage d'un message d'erreur ou tout autre procedure ici
            }
        });

        //get catecorie by id
        DAOCat.GetById(idCat,new VolleyCallback<Categorie>() {
            @Override
            public void onSuccess(Categorie result) {
                //Si la requete passe avec succès, les données seront accessibles dans result

                cat = result;
                Log.i(TAG, "onSuccess by id cat: "+cat.getLibelle());
                //Faire les traitements ici
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //En cas d'echec, prevoir l'affichage d'un message d'erreur ou tout autre procedure ici
            }
        });


        //Get ALl Chambre
        /*DAO.GetAll(new VolleyCallback<List<Chambre>>() {
            @Override
            public void onSuccess(List<Chambre> result) {
            //Si la requete passe avec succès, les données seront accessibles dans result
                chambres = result;
                Log.i(TAG, "onSuccess: "+chambres.get(0).getCategorie().getLibelle());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //En cas d'echec, prevoir l'affichage d'un message d'erreur ou tout autre procedure ici
            }
        });*/

        //Get chambre by id
        /*DAO.GetById(id,new VolleyCallback<Chambre>() {
            @Override
            public void onSuccess(Chambre result) {
            //Si la requete passe avec succès, les données seront accessibles dans result
                c = result;
                Log.i(TAG, "onSuccess by id: "+c.getNbLits());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError by id: "+e);
                //En cas d'echec, prevoir l'affichage d'un message d'erreur ou tout autre procedure ici
            }
        });*/

        //get chambres by categorie
        DAO.GetChambreByCategorie(idCat, new VolleyCallback<List<Chambre>>() {
            @Override
            public void onSuccess(List<Chambre> result) {
                //Si la requete passe avec succès, les données seront accessibles dans result
                chambresByCat = result;
                Log.i(TAG, "onSuccess: Liste des chambres by cat "+chambresByCat.size());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError: "+e);
                //En cas d'echec, prevoir l'affichage d'un message d'erreur ou tout autre procedure ici
            }
        });

        return frb.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}