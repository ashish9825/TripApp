
package com.ashishpanjwani.tripapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("tripId")
    @Expose
    private Integer tripId;
    @SerializedName("tripNumber")
    @Expose
    private String tripNumber;
    @SerializedName("scheduledTripDate")
    @Expose
    private String scheduledTripDate;
    @SerializedName("scheduledTripTime")
    @Expose
    private String scheduledTripTime;
    @SerializedName("numberOfEmployees")
    @Expose
    private Integer numberOfEmployees;
    @SerializedName("tripRouteLocation")
    @Expose
    private String tripRouteLocation;
    @SerializedName("assignedDriver")
    @Expose
    private Object assignedDriver;
    @SerializedName("assignedVehicle")
    @Expose
    private Object assignedVehicle;
    @SerializedName("escort")
    @Expose
    private Escort escort;
    @SerializedName("tripPoints")
    @Expose
    private List<TripPoint> tripPoints = null;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getScheduledTripDate() {
        return scheduledTripDate;
    }

    public void setScheduledTripDate(String scheduledTripDate) {
        this.scheduledTripDate = scheduledTripDate;
    }

    public String getScheduledTripTime() {
        return scheduledTripTime;
    }

    public void setScheduledTripTime(String scheduledTripTime) {
        this.scheduledTripTime = scheduledTripTime;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getTripRouteLocation() {
        return tripRouteLocation;
    }

    public void setTripRouteLocation(String tripRouteLocation) {
        this.tripRouteLocation = tripRouteLocation;
    }

    public Object getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(Object assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public Object getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(Object assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public Escort getEscort() {
        return escort;
    }

    public void setEscort(Escort escort) {
        this.escort = escort;
    }

    public List<TripPoint> getTripPoints() {
        return tripPoints;
    }

    public void setTripPoints(List<TripPoint> tripPoints) {
        this.tripPoints = tripPoints;
    }

}
