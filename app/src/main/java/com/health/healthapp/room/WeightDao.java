package com.health.healthapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeightDao {
    @Query("SELECT * FROM WeightResult ORDER BY id DESC")
    List<WeightResult> getAll();

    @Query("SELECT * FROM WeightResult WHERE id IN (:musicIds)")
    List<WeightResult> loadAllByIds(int[] musicIds);

    @Insert
    void insertAll(WeightResult... weightResults);

    @Delete
    void delete(WeightResult weightResult);
}