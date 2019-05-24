package com.ashishpanjwani.tripapp.Interfaces;

import com.ashishpanjwani.tripapp.Model.Drivers;
import com.ashishpanjwani.tripapp.Model.Escort;
import com.ashishpanjwani.tripapp.Model.Trip;
import com.ashishpanjwani.tripapp.Model.TripPoint;
import com.ashishpanjwani.tripapp.Model.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class TripAPIs {

    private static String BASE_URL = "http://private-909f9-appassignment.apiary-mock.com";

    public static TripFinder tripFinder = null;

    public static TripFinder getTripFinder() {

        if (tripFinder == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            tripFinder = retrofit.create(TripFinder.class);
        }
        return tripFinder;
    }

    public interface TripFinder {

    @GET("/drivers")
    Call<List<Drivers>> getDrivers();

    @GET("/vehicles")
    Call<List<Vehicle>> getVehicles();

    @GET("/trips")
    Call<List<Trip>> getTripDetails();
    Call<List<TripPoint>> getTripPoints();
    Call<List<Escort>> getTripEscorts();

    }
}
