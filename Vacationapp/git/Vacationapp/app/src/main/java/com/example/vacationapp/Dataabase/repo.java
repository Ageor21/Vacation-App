package com.example.vacationapp.Dataabase;

import android.app.Application;


import com.example.vacationapp.DAO.ItemsDAO;
import com.example.vacationapp.DAO.vacationsDAO;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class repo {
    private static ItemsDAO mItemsDAO;
    private static vacationsDAO mvacationsDAO;
    private static List<items> mAllitems;
    private static List<vacations> mAllvacations;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public repo(Application application){
        VacationDatabase db=VacationDatabase.getDatabase(application);
        mItemsDAO=db.itemsDAO();
        mvacationsDAO=db.vacationsDAO();
    }
    public static List<vacations> getAllVacations(){
        databaseExecutor.execute(()->{
            mAllvacations =mvacationsDAO.getAllvacations();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllvacations;
    }
    public static void insert(vacations Vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.insert(Vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(vacations Vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.update(Vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(vacations Vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.delete(Vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static List<items>getAllitems(){
        databaseExecutor.execute(()->{
            mAllitems =mItemsDAO.getAllitems();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllitems;
    }
    public static void insert(items Items){
        databaseExecutor.execute(()->{
            mItemsDAO.insert(Items);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void update(items items){
        databaseExecutor.execute(()->{
            mItemsDAO.update(items);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(items items){
        databaseExecutor.execute(()->{
            mItemsDAO.delete(items);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

