package com.example.vacationapp.Enity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trips")
public class vacations {
    @PrimaryKey(autoGenerate = true)
    private int tripsID;
    private String tripsTitle;
    private String tripsHotel;
    private String startDate;
    private String endDate;

    public vacations(int tripsID, String tripsTitle, String tripsHotel, String startDate, String endDate) {
        this.tripsID = tripsID;
        this.tripsTitle = tripsTitle;
        this.tripsHotel = tripsHotel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTripsID() {
        {return tripsID;}
    }

    public void setTripsID(int tripsID) {
        {this.tripsID = tripsID;}
    }

    public String getTripsTitle() {
        {return tripsTitle;}
    }

    public void setTripsTitle(String tripsTitle) {
        {this.tripsTitle = tripsTitle;}
    }

    public String getTripsHotel() {
        {return tripsHotel;}
    }

    public void setTripsHotel(String tripsHotel) {
        {this.tripsHotel = tripsHotel;}
    }

    public String getStartDate() {
        {return startDate;}
    }

    public void setStartDate(String startDate) {
        {this.startDate = startDate;}
    }

    public String getEndDate() {
        {return endDate;}
    }

    public void setEndDate(String endDate) {
            {this.endDate = endDate;}
    }
}
