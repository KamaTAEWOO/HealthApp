package com.health.healthapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WeightResult.class}, version = 1)
public abstract class WeightDB extends RoomDatabase {

    private static WeightDB INSTANCE = null;

    public abstract WeightDao weightDao();


    public static WeightDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    WeightDB.class, "music.db").build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}