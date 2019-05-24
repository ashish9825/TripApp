package com.ashishpanjwani.tripapp;

import com.ashishpanjwani.tripapp.Model.Drivers;
import com.ashishpanjwani.tripapp.Model.Vehicle;

public class JSONResponse {

    private Drivers[] drivers;
    private Vehicle[] vehicles;

    public Drivers[] getDrivers() {
        return drivers;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }
}
