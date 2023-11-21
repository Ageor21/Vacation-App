package com.example.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vacationapp.DAO.ItemsDAO;
import com.example.vacationapp.Dataabase.repo;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;
import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Vacations extends AppCompatActivity {
    private repo Repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacations);
        FloatingActionButton fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Vacations.this, Excursions.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.vacation_menu_list, menu);

        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.vaca_lists){
            Repo=new repo(getApplication());
            vacations Vacation=new vacations(0, "Bermuda Trip","The Plaza", "10/24/2023", "11/4/2023");
            Repo.insert(Vacation);
            Vacation=new vacations(0,"Spring Break","The Ritz London","03/20/2023", "4/10/2023");
            Repo.insert(Vacation);
            Vacation=new vacations(0, "London Trip","Grand Hotel", "6/12/2023", "6/29/2023");
            Repo.insert(Vacation);
            Repo=new repo(getApplication());
            items Item =new items(0, "bag", 100.0);
            Repo.insert(Item);
            return true;
        }
        if(item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return true;
    }

}