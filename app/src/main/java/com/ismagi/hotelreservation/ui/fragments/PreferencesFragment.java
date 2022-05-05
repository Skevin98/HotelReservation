package com.ismagi.hotelreservation.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.FragmentPreferencesBinding;

public class PreferencesFragment extends Fragment implements View.OnClickListener {

    FragmentPreferencesBinding fpb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fpb =  FragmentPreferencesBinding.inflate(inflater, container, false);

        return fpb.getRoot();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onClick(View view) {

    }
}