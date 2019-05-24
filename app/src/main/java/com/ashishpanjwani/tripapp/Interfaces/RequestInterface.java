package com.ashishpanjwani.tripapp.Interfaces;

import com.ashishpanjwani.tripapp.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("http://private-909f9-appassignment.apiary-mock.com/drivers")
    Call<JSONResponse> getJSON();

    @GET("http://private-909f9-appassignment.apiary-mock.com/vehicles")
    Call<JSONResponse> getJSONVehicles();
}
