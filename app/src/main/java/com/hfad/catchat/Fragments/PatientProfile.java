package com.hfad.catchat.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.hfad.catchat.R;


public class PatientProfile extends Fragment {

    View layout;
    TextView name_view,phone_view,place_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_profile);
        String name_key = getArguments().getString("name");
        String phone_key = getArguments().getString("phone");
        String place_key = getArguments().getString("place");

        layout = inflater.inflate(R.layout.fragment_patient_details, container, false);
        //getActivity().setTitle("Patient Details");

        //((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Your Title");


        name_view = (TextView) layout.findViewById(R.id.profile_name);
        phone_view = (TextView) layout.findViewById(R.id.profile_phone);
        place_view = (TextView) layout.findViewById(R.id.profile_place);

        name_view.setText(name_key);
        phone_view.setText(phone_key);
        place_view.setText(place_key);

        return layout;
    }


}
