package com.example.vacationapp.Enity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class items {
    @PrimaryKey(autoGenerate = true)
    private int itemID;
    private String itemName;
    private String Date;
    private  int  VacationID;


    public items(int itemID, String itemName, String Date, int VacationID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.Date = Date;
        this.VacationID = VacationID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
    public int getVacationID() {
        return VacationID;
    }

    public void setVacationID(int VacationID) {
        this.VacationID = VacationID;
    }


}
