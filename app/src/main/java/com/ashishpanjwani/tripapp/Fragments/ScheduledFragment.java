package com.ashishpanjwani.tripapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashishpanjwani.tripapp.Adapter.TripAdapter;
import com.ashishpanjwani.tripapp.Adapter.TripPointAdapter;
import com.ashishpanjwani.tripapp.Interfaces.CustomClickListener;
import com.ashishpanjwani.tripapp.Interfaces.TripAPIs;
import com.ashishpanjwani.tripapp.Model.Trip;
import com.ashishpanjwani.tripapp.Model.TripPoint;
import com.ashishpanjwani.tripapp.R;
import com.ashishpanjwani.tripapp.TripDetails;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduledFragment extends Fragment {

    RecyclerView recyclerView;
    TripAdapter tripAdapter;
    public static int Id;

    public ScheduledFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_scheduled);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getTrips();
        return view;
    }

    private void getTrips() {

        Call<List<Trip>> tripCall = TripAPIs.getTripFinder().getTripDetails();

        //Creating an anonymous callback
        tripCall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                //On response we will read the trip details
                Log.d("Trip Details", new Gson().toJson(response.body()));
                final List<Trip> trips = response.body();

                tripAdapter = new TripAdapter(getActivity(), trips, new CustomClickListener() {
                    @Override
                    public void onRouteClick(View v, int position) {
                        Id = trips.get(position).getTripId();
                        Intent intent = new Intent(getActivity(), TripDetails.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(tripAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Log.d(getActivity()+"",""+t);
            }
        });
    }


}
