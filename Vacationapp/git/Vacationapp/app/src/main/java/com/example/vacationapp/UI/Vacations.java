package com.example.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vacationapp.Dataabase.repo;
import com.example.vacationapp.Enity.vacations;
import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class Vacations extends AppCompatActivity {
    private repo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list_sub);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vacations.this, VacationDetails.class);
                startActivity(intent);
            }
        });
        repository = new repo(getApplication());
        List<vacations> allVacations = repository.getAllVacations();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final Adapter2 productAdapter = new Adapter2(this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter.setProducts(allVacations);
    }


    @Override
    protected void onResume() {

        super.onResume();
        List<vacations> allProducts = repository.getAllVacations();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final Adapter2 adapter2 = new Adapter2(this);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter2.setProducts(allProducts);
    }
}
