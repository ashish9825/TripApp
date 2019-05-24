
package com.ashishpanjwani.tripapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripPoint {

    @SerializedName("employeeID")
    @Expose
    private String employeeID;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("startOTP")
    @Expose
    private String startOTP;
    @SerializedName("endOTP")
    @Expose
    private String endOTP;
    @SerializedName("locality")
    @Expose
    private String locality;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStartOTP() {
        return startOTP;
    }

    public void setStartOTP(String startOTP) {
        this.startOTP = startOTP;
    }

    public String getEndOTP() {
        return endOTP;
    }

    public void setEndOTP(String endOTP) {
        this.endOTP = endOTP;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

}
