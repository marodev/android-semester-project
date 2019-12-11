package com.restervator.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation_table")
public class Reservation {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    private int numOfPersons;

    public Reservation(@NonNull String name, String image, String date, String time, int numOfPersons) {
        this.name = name;
        this.image = image;
        this.date = date;
        this.time = time;
        this.numOfPersons = numOfPersons;

    }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getNumOfPersons() {
        return numOfPersons;
    }
}
