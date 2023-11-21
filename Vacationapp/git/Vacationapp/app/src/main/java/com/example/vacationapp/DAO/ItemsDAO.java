package com.example.vacationapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vacationapp.Enity.items;

import java.util.List;

@Dao
public interface ItemsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(items items);

    @Update
    void update(items items);

    @Delete
    void delete(items items);

    @Query("SELECT * FROM items ORDER BY itemID ASC")
    List<items> getAllitems();
}
