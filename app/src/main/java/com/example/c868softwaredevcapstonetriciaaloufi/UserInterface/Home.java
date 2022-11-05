package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.AssignmentAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.SemesterAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.AssignmentDAO;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Database;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    Assignments assignments;
    Classes classes;
    Semesters semesters;
    RecyclerView recyclerView;
    Repository repository;

    RadioButton assignRad;
    RadioButton classRad;
    RadioButton semesterRad;

    AssignmentAdapter assignmentAdapter;
    ClassAdapter classAdapter;
    SemesterAdapter semesterAdapter;

    ArrayList<Assignments> filteredAssignments;
    List<Assignments> searchAssign;
    ArrayList<Classes> filteredClasses;
    List<Classes> searchClasses;
    ArrayList<Semesters> filterSemesters;
    List<Semesters> searchSemester;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_activity);
        semesterRad = (RadioButton) findViewById(R.id.semesterRadBtn);
        classRad = (RadioButton) findViewById(R.id.classesRadBtn);
        assignRad = (RadioButton) findViewById(R.id.assignRadBtn);
        final Button go = (Button) findViewById(R.id.mainAddButton);
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        repository = new Repository(getApplication());
        List<Semesters> semesters = repository.getAllSemesters();
        List<Classes> classes = repository.getAllClasses();
        List<Assignments> assignments = repository.getAllAssignments();
        semesterAdapter = new SemesterAdapter(this);
        classAdapter = new ClassAdapter(this);
        assignmentAdapter = new AssignmentAdapter(this);
        recyclerView.setAdapter(semesterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        semesterAdapter.setSemesters(semesters);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (semesterRad.isChecked()) {
                    Intent intent = new Intent(Home.this, AddSemester.class);
                    startActivity(intent);
                } else if (classRad.isChecked()) {
                    Intent intent = new Intent(Home.this, AddClass.class);
                    startActivity(intent);
                } else if (assignRad.isChecked()) {
                    Intent intent = new Intent(Home.this, AddAssignment.class);
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
        repository = new Repository(getApplication());
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
        repository = new Repository(getApplication());
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
        repository = new Repository(getApplication());
        List<Assignments> assignments = repository.getAllAssignments();
        assignmentAdapter = new AssignmentAdapter(this);
        recyclerView.setAdapter(assignmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignmentAdapter.setAssignments(assignments);
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (assignRad.isChecked()) {
                    searchAssign = repository.getAllAssignments();
                    filteredAssignments = new ArrayList<>();
                    for (Assignments assignments : searchAssign) {
                        if (assignments.getAssignmentTitle().toLowerCase().contains(newText.toLowerCase().trim()) ||
                                assignments.getAssignmentType().toLowerCase().contains(newText.toLowerCase().trim()) ||
                                assignments.getStartDate().contains(newText.trim()) ||
                                assignments.getEndDate().contains(newText.trim())) {
                            filteredAssignments.add(assignments);
                        }
                        AssignmentAdapter assignAdapter = new AssignmentAdapter(Home.this);
                        recyclerView.setAdapter(assignAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                        assignAdapter.setAssignments(filteredAssignments);
                    }
               } else if (classRad.isChecked()) {
                    searchClasses = repository.getAllClasses();
                    filteredClasses = new ArrayList<>();
                    for (Classes classes : searchClasses) {
                        if (classes.getClassName().toLowerCase().contains(newText.toLowerCase().trim()) || classes.getStartDate().contains(newText.trim()) ||
                                classes.getEndDate().contains(newText.trim()) || classes.getCourseStatus().toLowerCase().contains(newText.toLowerCase().trim()) ||
                                classes.getInstructorName().toLowerCase().contains(newText.toLowerCase().trim()) ||
                                classes.getInstructorPhone().contains(newText.trim()) ||
                                classes.getInstructorEmail().toLowerCase().contains(newText.toLowerCase().trim())) {
                            filteredClasses.add(classes);
                        }
                        ClassAdapter classAdapter = new ClassAdapter(Home.this);
                        recyclerView.setAdapter(classAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                        classAdapter.setClasses(filteredClasses);
                    }

               }else if (semesterRad.isChecked()) {
                    searchSemester = repository.getAllSemesters();
                    filterSemesters = new ArrayList<>();
                    for (Semesters semesters : searchSemester) {
                        if (semesters.getSemesterName().toLowerCase().contains(newText.toLowerCase().trim()) || semesters.getStartDate().contains(newText.trim()) || semesters.getEndDate().contains(newText.trim())) {
                            filterSemesters.add(semesters);
                        }
                        SemesterAdapter semesterAdapter = new SemesterAdapter(Home.this);
                        recyclerView.setAdapter(semesterAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                        semesterAdapter.setSemesters(filterSemesters);
                    }
                }else {
                        Toast.makeText(Home.this, "Search Error", Toast.LENGTH_SHORT).show();
                    }
                return false;
            }
        });
        return false;
    }
}

