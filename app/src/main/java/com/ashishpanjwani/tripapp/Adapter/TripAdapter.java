package com.ashishpanjwani.tripapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashishpanjwani.tripapp.AssignDriver;
import com.ashishpanjwani.tripapp.AssignVehicle;
import com.ashishpanjwani.tripapp.Interfaces.CustomClickListener;
import com.ashishpanjwani.tripapp.Interfaces.TripAPIs;
import com.ashishpanjwani.tripapp.Model.Trip;
import com.ashishpanjwani.tripapp.Model.TripPoint;
import com.ashishpanjwani.tripapp.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private Context context;
    List<Trip> tripList;
    int clickCount = 0;
    TripPointAdapter tripPointAdapter;
    CustomClickListener listener;

    public TripAdapter(Context context, List<Trip> tripList, CustomClickListener listener) {
        this.context = context;
        this.tripList = tripList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trips_item, viewGroup , false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRouteClick(v,mViewHolder.getLayoutPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Trip trip = tripList.get(i);
        viewHolder.tripDate.setText(trip.getScheduledTripDate());
        viewHolder.tripNumber.setText(trip.getTripNumber());
        viewHolder.tripTime.setText(trip.getScheduledTripTime());
        viewHolder.tripRoute.setText(trip.getTripRouteLocation());
        viewHolder.noOfEmployees.setText(trip.getNumberOfEmployees());
        viewHolder.recyclerViewTrip.setLayoutManager(new LinearLayoutManager(context));
        getTripPoints();
        viewHolder.recyclerViewTrip.setAdapter(tripPointAdapter);

        viewHolder.expandImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount = clickCount + 1;
                if (clickCount == 1) {
                    viewHolder.expandImage.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
                    viewHolder.recyclerViewTrip.setVisibility(View.VISIBLE);
                }

                if (clickCount % 2 == 0) {
                    viewHolder.expandImage.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp);
                    viewHolder.recyclerViewTrip.setVisibility(View.GONE);
                } else {
                    viewHolder.expandImage.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp);
                    viewHolder.recyclerViewTrip.setVisibility(View.VISIBLE);
                }
            }
        });

        viewHolder.assignDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AssignDriver.class);
                context.startActivity(intent);
            }
        });

        viewHolder.asssignVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AssignVehicle.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tripDate;
        TextView tripNumber;
        TextView tripTime;
        TextView noOfEmployees;
        TextView tripRoute;
        RecyclerView recyclerViewTrip;
        ImageView expandImage;
        CardView assignDriver;
        CardView asssignVehicle;

        public ViewHolder(View view) {
            super(view);
            tripDate = view.findViewById(R.id.trip_date);
            tripNumber = view.findViewById(R.id.trip_number);
            tripTime = view.findViewById(R.id.trip_time);
            noOfEmployees = view.findViewById(R.id.no_of_employees);
            tripRoute = view.findViewById(R.id.trip_route);
            recyclerViewTrip = view.findViewById(R.id.recyclerview_trip_points);
            expandImage = view.findViewById(R.id.expand_image);
            assignDriver = view.findViewById(R.id.driver_card);
            asssignVehicle = view.findViewById(R.id.vehicle_card);
        }
    }

    private void getTripPoints() {

        Call<List<TripPoint>> tripPointsCall = TripAPIs.getTripFinder().getTripPoints();

        tripPointsCall.enqueue(new Callback<List<TripPoint>>() {
            @Override
            public void onResponse(Call<List<TripPoint>> call, Response<List<TripPoint>> response) {
                //On Response we will read the trip points
                Log.d("Trip Points",new Gson().toJson(response.body()));
                final List<TripPoint> tripPoints = response.body();

                tripPointAdapter = new TripPointAdapter(context,tripPoints);
            }

            @Override
            public void onFailure(Call<List<TripPoint>> call, Throwable t) {
                Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
