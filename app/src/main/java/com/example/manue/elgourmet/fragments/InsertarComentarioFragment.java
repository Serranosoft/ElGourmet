package com.example.manue.elgourmet.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manue.elgourmet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertarComentarioFragment extends Fragment {


    public InsertarComentarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insertar_comentario, container, false);
    }

}
