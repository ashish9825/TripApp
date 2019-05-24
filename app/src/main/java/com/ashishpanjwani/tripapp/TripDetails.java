package com.ashishpanjwani.tripapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashishpanjwani.tripapp.Adapter.TripAdapter;
import com.ashishpanjwani.tripapp.Adapter.TripPointDetailsAdapter;
import com.ashishpanjwani.tripapp.Fragments.ScheduledFragment;
import com.ashishpanjwani.tripapp.Interfaces.TripAPIs;
import com.ashishpanjwani.tripapp.Model.Drivers;
import com.ashishpanjwani.tripapp.Model.Escort;
import com.ashishpanjwani.tripapp.Model.Trip;
import com.ashishpanjwani.tripapp.Model.TripPoint;
import com.ashishpanjwani.tripapp.Model.Vehicle;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripDetails extends AppCompatActivity {

    TextView tripNumber;
    TextView startTime;
    RecyclerView recyclerView;
    TripPointDetailsAdapter adapter;
    TextView escortName;
    TextView escortId;
    TextView driverNumber;
    TextView driverName;
    TextView carName;
    TextView carNumber;
    ImageView callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details);

        Toolbar toolbar = findViewById(R.id.toolbar_tripDetails);
        setSupportActionBar(toolbar);
        tripNumber = findViewById(R.id.trip_number);
        startTime = findViewById(R.id.scheduled_starttime);
        recyclerView = findViewById(R.id.recyclerview_trip_points);
        recyclerView.setLayoutManager(new LinearLayoutManager(TripDetails.this));
        escortName = findViewById(R.id.escort_name);
        escortId = findViewById(R.id.escort_id);
        driverName = findViewById(R.id.driver_name);
        driverNumber = findViewById(R.id.driver_number);
        carName = findViewById(R.id.car_name);
        carNumber = findViewById(R.id.car_number);
        callButton = findViewById(R.id.action_call);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tripDetails();
        tripPoints();
    }

    private void tripDetails() {
        Call<List<Trip>> tripCall = TripAPIs.getTripFinder().getTripDetails();

        //Creating an anonymous callback
        tripCall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                //On response we will read the trip details
                Log.d("Trip Details", new Gson().toJson(response.body()));
                final List<Trip> trips = response.body();
                tripNumber.setText(trips.get(ScheduledFragment.Id).getTripNumber());
                startTime.setText(trips.get(ScheduledFragment.Id).getScheduledTripTime());
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Log.d(this+"",""+t);
            }
        });
    }

    private void tripPoints() {
        Call<List<TripPoint>> tripPointCall = TripAPIs.getTripFinder().getTripPoints();

        //Creating an anonymous callback
        tripPointCall.enqueue(new Callback<List<TripPoint>>() {
            @Override
            public void onResponse(Call<List<TripPoint>> call, Response<List<TripPoint>> response) {
                //On response we will read the trip details
                Log.d("Trip Details", new Gson().toJson(response.body()));
                final List<TripPoint> tripPoints = response.body();

                adapter = new TripPointDetailsAdapter(TripDetails.this,tripPoints);
                recyclerView.setAdapter(adapter);
                escortDetails();
                vehicleDetails();
                driverDetails();
            }

            @Override
            public void onFailure(Call<List<TripPoint>> call, Throwable t) {
                Toast.makeText(TripDetails.this, "Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void escortDetails() {
        Call<List<Escort>> escortCall = TripAPIs.getTripFinder().getTripEscorts();

        //Creating an anonymous callback
        escortCall.enqueue(new Callback<List<Escort>>() {
            @Override
            public void onResponse(Call<List<Escort>> call, Response<List<Escort>> response) {
                //On Response we will read the escort details
                Log.d("Escort Details : ",new Gson().toJson(response.body()));
                final  List<Escort> escorts = response.body();
                escortName.setText(escorts.get(1).getName());
                escortId.setText(escorts.get(1).getId());
            }

            @Override
            public void onFailure(Call<List<Escort>> call, Throwable t) {
                Toast.makeText(TripDetails.this, "Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void vehicleDetails() {

        Call<List<Vehicle>> vehicleCall = TripAPIs.getTripFinder().getVehicles();
        vehicleCall.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                Log.d("Vehicle Details : ",new Gson().toJson(response.body()));
                final List<Vehicle> vehicles = response.body();
                carName.setText(vehicles.get(1).getName());
                carNumber.setText(vehicles.get(1).getRegistrationNumber());
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Toast.makeText(TripDetails.this, "Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void driverDetails() {

        Call<List<Drivers>> driversCall = TripAPIs.getTripFinder().getDrivers();
        driversCall.enqueue(new Callback<List<Drivers>>() {
            @Override
            public void onResponse(Call<List<Drivers>> call, Response<List<Drivers>> response) {
                Log.d("Driver Details", new Gson().toJson(response.body()));
                final List<Drivers> drivers = response.body();
                driverName.setText(drivers.get(1).getName());
                driverNumber.setText(drivers.get(1).getMobileNo());

                callButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phoneNo="+91"+drivers.get(1).getMobileNo();
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+phoneNo));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Drivers>> call, Throwable t) {
                Toast.makeText(TripDetails.this, "Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
