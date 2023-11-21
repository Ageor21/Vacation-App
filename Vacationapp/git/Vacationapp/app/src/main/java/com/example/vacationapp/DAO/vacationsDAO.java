package com.example.vacationapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vacationapp.Enity.vacations;

import java.util.List;

@Dao
public interface vacationsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(vacations vacations);

    @Update
    void update(vacations vacations);

    @Delete
    void delete(vacations vacations);

    @Query("SELECT * FROM trips ORDER BY tripsID ASC")
    List<vacations> getAllvacations();
}

