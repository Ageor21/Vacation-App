package com.example.vacationapp.UI;



import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationapp.Dataabase.repo;
import com.example.vacationapp.Enity.items;
import com.example.vacationapp.Enity.vacations;
import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.DatePickerDialog;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemsDetails extends AppCompatActivity {
    String Excursions;
    int excursionsID;
    int vacationsID;
    EditText editExcursions;
    items currentExcursions;

    TextView editDate;
    repo repository;
    DatePickerDialog.OnDateSetListener StartDate;

    final Calendar myCalendarStart = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_details);
        Excursions = getIntent().getStringExtra("Item_Name");
        editExcursions = findViewById(R.id.excursion);
        editExcursions.setText(Excursions);
        excursionsID = getIntent().getIntExtra("ID", -1);
        vacationsID = getIntent().getIntExtra("VacationID", -1);
        editDate = findViewById(R.id.Dates);
        String myFormat = "mm/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        repository = new repo(getApplication());

        ArrayList<items> productArrayList = new ArrayList<>();
        productArrayList.addAll(repository.getAllitems());
        ArrayList<Integer> productIdList = new ArrayList<>();
        for (items product : productArrayList) {
            productIdList.add(product.getItemID());
        }
        ArrayAdapter<Integer> productIdAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, productIdList);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(productIdAdapter);

        StartDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }

        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Date date;
                String info = editDate.getText().toString();
                if (info.equals("")) info = "12/17/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(ItemsDetails.this, StartDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "mm/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendarStart.getTime()));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items_mod, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        if (item.getItemId() == R.id.save_excursions) {
            items excursion;
            if (excursionsID == -1) {
                if (repository.getAllitems().size() == 0)
                    excursionsID = 1;
                else
                    excursionsID = repository.getAllitems().get(repository.getAllitems().size() - 1).getItemID() + 1;
                excursion = new items(excursionsID, editExcursions.getText().toString(), editDate.getText().toString(), vacationsID);
                repository.insert(excursion);
            } else {
                excursion = new items(excursionsID, editExcursions.getText().toString(), editDate.getText().toString(), vacationsID);
                repository.update(excursion);
            }
            return true;
        }
        if (item.getItemId() == R.id.delete_excursions) {
            for (items prod : repository.getAllitems()) {
                if (prod.getVacationID() == vacationsID) currentExcursions = prod;
            }
            excursionsID = 1;
            for (items part : repository.getAllitems()) {
                if (part.getVacationID() == vacationsID) ++vacationsID;
            }
            if (vacationsID >= -1) {
                repository.delete(currentExcursions);
                Toast.makeText(ItemsDetails.this, currentExcursions.getItemName() + " was deleted", Toast.LENGTH_LONG).show();{

                }
                return true;
            }
            if (item.getItemId() == R.id.notification2) {
                String dateFromScreen = editDate.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Long trigger = myDate.getTime();
                    Intent intent = new Intent(ItemsDetails.this, MyReceiver.class);
                    String format = String.format(editExcursions.getText().toString() + "is today");
                    intent.putExtra("Ending", format);
                    PendingIntent sender = PendingIntent.getBroadcast(ItemsDetails.this, ++HomeScreen.numAlert + 1, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                } catch (Exception e) {
                }
                return true;
            }

        }
            return super.onOptionsItemSelected(item);
        }
    }

