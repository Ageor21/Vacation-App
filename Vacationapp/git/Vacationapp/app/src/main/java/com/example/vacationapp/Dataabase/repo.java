package com.example.vacationapp.Dataabase;

import android.app.Application;


import com.example.vacationapp.DAO.ItemsDAO;
import com.example.vacationapp.DAO.vacationsDAO;
import com.example.vacationapp.Dataabase.VacationDatabase;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class repo {
    private final ItemsDAO mItemsDAO;
    private final vacationsDAO mvacationsDAO;
    private List<items> mAllitems;
    private List<vacations> mAllvacations;

    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public repo(Application application){
        VacationDatabase db=VacationDatabase.getDatabase(application);
        mItemsDAO=db.itemsDAO();
        mvacationsDAO=db.vacationsDAO();
    }
    public List<vacations>getmAllvacations(){
        databaseExecutor.execute(()->{
            mAllvacations=mvacationsDAO.getAllvacations();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllvacations;
    }
    public void insert(vacations vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.insert(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(vacations vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.update(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(vacations vacations){
        databaseExecutor.execute(()->{
            mvacationsDAO.delete(vacations);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<items>getAllParts(){
        databaseExecutor.execute(()->{
            mAllitems=mItemsDAO.getAllitems();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllitems;
    }
    public void insert(items items){
        databaseExecutor.execute(()->{
            mItemsDAO.insert(items);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(items items){
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

