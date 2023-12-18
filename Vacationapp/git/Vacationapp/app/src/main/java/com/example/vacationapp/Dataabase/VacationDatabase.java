package com.example.vacationapp.Dataabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vacationapp.DAO.ItemsDAO;
import com.example.vacationapp.DAO.vacationsDAO;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;

@Database(entities = {vacations.class, items.class}, version = 11, exportSchema = false)
public abstract class VacationDatabase extends RoomDatabase {
    public abstract vacationsDAO vacationsDAO();

    public abstract ItemsDAO itemsDAO();

    private static volatile VacationDatabase INSTANCE;

    public static VacationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VacationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VacationDatabase.class, "MyVacationDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }
        }
            return INSTANCE;
        }
    }
