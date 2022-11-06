package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassReportAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.SemesterAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClassStatusReport extends AppCompatActivity {

    Spinner classStatusSpinner;

    RecyclerView classReportView;

    TextView classReportTimeStamp;

    TextView classReportTxt;

    String classStatus;

    Repository repository;

    ClassReportAdapter classAdapter;

    List<Classes> classesFull;

    ArrayList<Classes> filteredClasses;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_report);
        classStatusSpinner = findViewById(R.id.classStatusSpinner);
        classReportView = findViewById(R.id.classReportTableView);
        classReportTimeStamp = findViewById(R.id.classReportTimeStamp);
        classReportTimeStamp.setText("");
        classReportTxt = findViewById(R.id.classReportTxt);
        repository = new Repository(getApplication());
        classAdapter = new ClassReportAdapter(this);
        classesFull = repository.getAllClasses();
        classStatus = String.valueOf(classStatusSpinner.getSelectedItem());
        classReportView.setAdapter(classAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        classReportView.setLayoutManager(layoutManager);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerAdapter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        classStatusSpinner.setAdapter(adapter);
        classAdapter.setClasses(classesFull);
       int currentSelection = classStatusSpinner.getSelectedItemPosition();
       classStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (currentSelection != position)  {
                   classReportTimeStamp.setText("Report Generated On: " + new Timestamp(System.currentTimeMillis()));
                   classesFull = repository.getAllClasses();
                   filteredClasses = new ArrayList<>();
                   for (Classes classes : classesFull) {
                       if (classStatusSpinner.getSelectedItem().toString().contentEquals(classes.getCourseStatus())) {
                           filteredClasses.add(classes);
                       }
                       classAdapter.setClasses(filteredClasses);
                   }
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
               Toast.makeText(ClassStatusReport.this, "Please select a class status.", Toast.LENGTH_SHORT).show();
           }
       });
    }

}
