package com.example.manue.elgourmet.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manue.elgourmet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCommentsFragment extends Fragment {


    public ViewCommentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comentarios, container, false);



        return view;

    }

}
