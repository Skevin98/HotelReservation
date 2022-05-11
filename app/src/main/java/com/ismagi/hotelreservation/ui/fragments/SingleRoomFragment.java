package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.DAO.IDao;
import com.ismagi.hotelreservation.DAO.VolleyCallback;
import com.ismagi.hotelreservation.models.Chambre;
import com.ismagi.hotelreservation.databinding.FragmentSingleRoomBinding;

public class SingleRoomFragment extends Fragment {

    private static final String TAG = "SingleRoomFragment";
    FragmentSingleRoomBinding fsrp;

    IDao<Chambre> DAO;
    Chambre c = new Chambre();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fsrp = FragmentSingleRoomBinding.inflate(inflater, container, false);


        String id = "ffffffff-5555-4562-b3fc-2c963f66afa6";
        DAO.GetById(id,new VolleyCallback<Chambre>() {
            @Override
            public void onSuccess(Chambre result) {
                c = result;
                Log.i(TAG, "onSuccess by id: "+c.getNbLits());
            }

            @Override
            public void onError(String e) {
                Log.i(TAG, "onError by id: "+e);
            }
        });

        return fsrp.getRoot();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}