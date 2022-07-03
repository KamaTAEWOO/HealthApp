package com.health.healthapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class WeightResult {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="date")
    public String date;

    @ColumnInfo(name="pastWeight")
    public double pastWeight;

    @ColumnInfo(name="currentWeight")
    public double currentWeight;
}