package com.example.vacationapp.UI;




import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vacationapp.Dataabase.repo;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;
import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;

import android.content.Context;

import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.Toast;


public class VacationDetails extends AppCompatActivity {
    String title;
    String hotel;
    int vacationID;
    int numExcursions;
    EditText editTitle;
    EditText editHotel;

    TextView editStart;
    TextView editEnd;
    repo repository;
    vacations currentVacations;
    items currentExcursions;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener EndDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        repository = new repo(getApplication());
        title = getIntent().getStringExtra("Vacation");
        editTitle = findViewById(R.id.title1);
        editTitle.setText(title);
        hotel = getIntent().getStringExtra("Hotel");
        editHotel = findViewById(R.id.hotel);
        editHotel.setText(hotel);
        vacationID = getIntent().getIntExtra("ID", -1);
        editStart = findViewById(R.id.Date);
        editEnd = findViewById(R.id.Date2);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.US);
        RecyclerView recyclerView = findViewById(R.id.partrecyclerview);


        final Adapter1 ExAdapter = new Adapter1(this);
        recyclerView.setAdapter(ExAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<items> filteredParts = new ArrayList<>();
        for (items p : repository.getAllitems()) {
            if (p.getVacationID() == vacationID) filteredParts.add(p);
        }
        ExAdapter.setParts(filteredParts);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationDetails.this, ItemsDetails.class);
                intent.putExtra("VacationID", vacationID);
                startActivity(intent);
            }
        });


    startDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
        int dayOfMonth) {
            myCalendarStart.set(Calendar.YEAR, year);
            myCalendarStart.set(Calendar.MONTH, monthOfYear);
            myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


            updateLabelStarts();
        }

    };

        editStart.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Date date;
            String info = editStart.getText().toString();
            if (info.equals("")) info = "12/15/23";
            try {
                myCalendarStart.setTime(sdf.parse(info));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(VacationDetails.this, startDate, myCalendarStart
                    .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                    myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
        }
    });


    EndDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
        int dayOfMonth) {
            myCalendarEnd.set(Calendar.YEAR, year);
            myCalendarEnd.set(Calendar.MONTH, monthOfYear);
            myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);


            updateLabelENDS();
        }

    };

        editEnd.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View V) {
            Date date;
            String info = editEnd.getText().toString();
            if (info.equals("")) info = "12/15/23";
            try {
                myCalendarEnd.setTime(sdf2.parse(info));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            new DatePickerDialog(VacationDetails.this, EndDate, myCalendarEnd
                    .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                    myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
        }
    });

}

    private void updateLabelStarts() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelENDS() {
        String myFormat2 = "MM/dd/yy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);

        editEnd.setText(sdf2.format(myCalendarEnd.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vacation_modifiying, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        if (item.getItemId() == R.id.notify_start) {
            String dateFromScreen = editStart.getText().toString();
            String myFormat2 = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat2, Locale.US);
            Date myDate2 = null;
            try {
                 myDate2 = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate2.getTime();
                Intent intent = new Intent(VacationDetails.this, MyReceiver2.class);
                String format = String.format("Your vacation to " + editTitle.getText().toString() + " is starting on " + editStart.getText().toString());
                intent.putExtra("Start", format);
                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++HomeScreen.numAlert + 1, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);} catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        if (item.getItemId() == R.id.notify_end) {
            String dateFromScreen = editEnd.getText().toString();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf2.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(VacationDetails.this, Information.class);
                String format = String.format("Your vacation to " + editTitle.getText().toString() + " is ending on " + editEnd.getText().toString());
                intent.putExtra("key", format);
                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++HomeScreen.numAlert + 5, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);} catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;}
        if (item.getItemId() == R.id.Notes) {
            String format = String.format("Your vacation to " + editTitle.getText().toString() + " starts " + editStart.getText().toString() + " and ends " + editEnd.getText().toString());
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, format);
            sendIntent.putExtra(Intent.EXTRA_TITLE, "Vacation Details");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }
        if (item.getItemId() == R.id.vacation_saved) {
            vacations Vacations;
            if (vacationID == -1) {
                if (repository.getAllVacations().size() == 0) vacationID = 1;
                else
                    vacationID = repository.getAllVacations().get(repository.getAllVacations().size() - 1).getTripsID() + 1;
                Vacations = new vacations(vacationID, editTitle.getText().toString(), editHotel.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
                repository.insert(Vacations);
            } else {
                try {
                    Vacations = new vacations(vacationID, editTitle.getText().toString(), editHotel.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
                    repository.update(Vacations);
                } catch (Exception e) {

                }
            }
            return true;
        }
        if (item.getItemId() == R.id.vacation_delete) {
            for (vacations prod : repository.getAllVacations()) {
                if (prod.getTripsID() == vacationID) currentVacations = prod;
            }
            numExcursions = 0;
            for (items part : repository.getAllitems()) {
                if (part.getVacationID() == vacationID) ++numExcursions;
            }
            if (numExcursions == 0) {
                repository.delete(currentVacations);
                Toast.makeText(VacationDetails.this, currentVacations.getTripsTitle() + " was deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(VacationDetails.this, "Can't delete a vacation with a excursion", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.partrecyclerview);
        final Adapter1 partAdapter = new Adapter1(this);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<items> filteredParts = new ArrayList<>();
        for (items p : repository.getAllitems()) {
            if (p.getVacationID() == vacationID) filteredParts.add(p);
        }
        partAdapter.setParts(filteredParts);

    }
}

