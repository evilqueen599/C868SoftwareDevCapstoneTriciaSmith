package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.AssignmentReportAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AssignmentTypeReport extends AppCompatActivity {

    Spinner assignTypeSpinner;
    RecyclerView assignReportView;
    TextView assignReportTimeStamp;
    TextView assignReportTxt;
    String assignType;
    Repository repository;
    AssignmentReportAdapter assignAdapter;
    List<Assignments> assignFull;
    ArrayList<Assignments> filteredAssign;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_report);
        assignTypeSpinner = findViewById(R.id.assignTypeSpinner);
        assignReportView = findViewById(R.id.assignReportView);
        assignReportTimeStamp = findViewById(R.id.assignReportTimeStamp);
        assignReportTimeStamp.setText("");
        assignReportTxt = findViewById(R.id.assignReportTxt);
        repository = new Repository(getApplication());
        assignAdapter = new AssignmentReportAdapter(this);
        assignFull = repository.getAllAssignments();
        assignType = String.valueOf(assignTypeSpinner.getSelectedItem());
        assignReportView.setAdapter(assignAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        assignReportView.setLayoutManager(layoutManager);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assignmentSpinnerAdapter, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        assignTypeSpinner.setAdapter(adapter);
        int currentSelection = assignTypeSpinner.getSelectedItemPosition();
        assignTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currentSelection != position)  {
                    assignReportTimeStamp.setText("Report Generated On: " + new Timestamp(System.currentTimeMillis()));
                    assignFull = repository.getAllAssignments();
                    filteredAssign = new ArrayList<>();
                    for (Assignments assignments : assignFull) {
                        if (assignTypeSpinner.getSelectedItem().toString().contentEquals(assignments.getAssignmentType())) {
                            filteredAssign.add(assignments);
                        }
                        assignAdapter.setAssignments(filteredAssign);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AssignmentTypeReport.this, "Please select an assignment type", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assign_report_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.home:
                Intent intent = new Intent(AssignmentTypeReport.this, Home.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

