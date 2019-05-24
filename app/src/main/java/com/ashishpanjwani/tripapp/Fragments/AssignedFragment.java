package com.ashishpanjwani.tripapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashishpanjwani.tripapp.Interfaces.TripAPIs;
import com.ashishpanjwani.tripapp.Model.Trip;
import com.ashishpanjwani.tripapp.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignedFragment extends Fragment {

    public AssignedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assigned, container, false);
    }
}
