package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.MenuActivity;
import com.ismagi.hotelreservation.adapters.CategorieAdapter;
import com.ismagi.hotelreservation.DAO.CategorieDAO;
import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.models.Categorie;
import com.ismagi.hotelreservation.databinding.FragmentCategorieBinding;

import java.util.ArrayList;
import java.util.List;

public class CategorieFragment extends Fragment {

    private static final String TAG = "CategorieFragment";

    public CategorieAdapter adapter;
    public FragmentCategorieBinding fcb;
    IDao<Categorie> DAO;
    List<Categorie> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fcb = FragmentCategorieBinding.inflate(inflater, container, false);
        fcb.CatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CategorieAdapter(getContext());
        categories = ((MenuActivity) getActivity()).GetCategories();
        /*DAO = new CategorieDAO(getContext());
        DAO.GetAll(new VolleyCallback<List<Categorie>>() {
            @Override
            public void onSuccess(List<Categorie> result) {
                categories = result;
                adapter.setCategories(categories);

            }

            @Override
            public void onError(String e) {

            }
        });*/

        adapter.setCategories(categories);
        Log.i(TAG, "onCreateView: "+adapter.getItemCount());
        //Log.i(TAG, "onCreateView liste: "+categories.size());

        fcb.setAdapter(adapter);
        return fcb.getRoot();
    }
}