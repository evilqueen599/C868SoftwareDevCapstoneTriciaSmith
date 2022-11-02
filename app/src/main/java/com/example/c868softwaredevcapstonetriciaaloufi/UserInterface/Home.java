package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.AssignmentAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.SemesterAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.util.List;

public class Home extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_activity);

        final RadioButton semesterRad = (RadioButton) findViewById(R.id.semesterRadBtn);
        final RadioButton classRad = (RadioButton) findViewById(R.id.classesRadBtn);
        final RadioButton assignRad = (RadioButton) findViewById(R.id.assignRadBtn);
        final Button go = (Button) findViewById(R.id.mainAddButton);

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        Repository repository = new Repository(getApplication());
        List<Semesters> semesters = repository.getAllSemesters();
        final SemesterAdapter semesterAdapter = new SemesterAdapter(this);
        recyclerView.setAdapter(semesterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        semesterAdapter.setSemesters(semesters);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (semesterRad.isChecked()) {
                    Intent intent = new Intent(Home.this,AddSemester.class);
                    startActivity(intent);
                } else if (classRad.isChecked()) {
                    Intent intent = new Intent(Home.this, AddClass.class);
                    startActivity(intent);
                } else if (assignRad.isChecked()) {
                    Intent intent = new Intent(Home.this,AddAssignment.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onClickSemester(View view) {
        TextView myTextView = (TextView) findViewById(R.id.semesterListTxt);
        myTextView.setText("Semesters");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("Add A New Semester");
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        Repository repository = new Repository(getApplication());
        List<Semesters> semesters = repository.getAllSemesters();
        final SemesterAdapter semesterAdapter = new SemesterAdapter(this);
        recyclerView.setAdapter(semesterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        semesterAdapter.setSemesters(semesters);
    }

    public void onClickClasses(View view) {
        TextView myTextView = (TextView) findViewById(R.id.semesterListTxt);
        myTextView.setText("Classes");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("Add A New Class");

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        Repository repository = new Repository(getApplication());
        List<Classes> classes = repository.getAllClasses();
        final ClassAdapter classAdapter = new ClassAdapter(this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        classAdapter.setClasses(classes);
    }

    public void onClickAssign(View view) {
        TextView myTextView = (TextView) findViewById(R.id.semesterListTxt);
        myTextView.setText("Assignments");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("Add A New Assignment");

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        Repository repository = new Repository(getApplication());
        List<Assignments> assignments = repository.getAllAssignments();
        final AssignmentAdapter assignmentAdapter = new AssignmentAdapter(this);
        recyclerView.setAdapter(assignmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignmentAdapter.setAssignments(assignments);
    }
}
