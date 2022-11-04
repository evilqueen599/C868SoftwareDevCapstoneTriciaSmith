package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.SemesterClassAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.MyBroadcastReceiver;
import com.example.c868softwaredevcapstonetriciaaloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddSemester extends AppCompatActivity {

    Repository repository;
    Semesters semesters;
    int semesterId;
    String semesterTitle;
    String startDate;
    String endDate;
    String editSemesterTitle;
    String editStartDate;
    String editEndDate;
    EditText semesterTitleTxt;
    Button startDatePickerButton;
    Button endDatePickerButton;
    Button createBtn;
    FloatingActionButton addClassesButton;
    DatePickerDialog datePickerDialog;
    DatePickerDialog endDatePickerDialog;
    List<Classes> assocClasses;
    List<Classes> classesInSemester;
    List<Classes> unassignedClasses;
    SemesterClassAdapter classAdapter;
    SimpleDateFormat formatter;
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_semester_activity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        format = "MM/dd/yyyy";
        formatter = new SimpleDateFormat(format, Locale.US);
        classAdapter = new SemesterClassAdapter(this);
        repository = new Repository(getApplication());
        addClassesButton = findViewById(R.id.addClassesBtn);
        semesterId = getIntent().getIntExtra("semesterId", -1);
        editSemesterTitle = getIntent().getStringExtra("semesterName");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");

        if (semesterId == -1) {
            setUpView();
        } else {
            semesterTitleTxt = findViewById(R.id.semesterTitleTxt);
            semesterTitleTxt.setText(editSemesterTitle);
            startDatePickerButton = findViewById(R.id.startDatePickerButton);
            startDatePickerButton.setText(editStartDate);
            endDatePickerButton = findViewById(R.id.endDatePickerButton);
            endDatePickerButton.setText(editEndDate);
            initDatePicker();
            initEndDatePicker();
            addSemester();
            assocCLassList();
        }
    }

    private void setUpView() {
        semesterTitleTxt = findViewById(R.id.semesterTitleTxt);
        startDatePickerButton = findViewById(R.id.startDatePickerButton);
        startDatePickerButton.setText(getEndDate());
        initDatePicker();
        initEndDatePicker();
        addClassesButton.hide();
        endDatePickerButton = findViewById(R.id.endDatePickerButton);
        endDatePickerButton.setText(getTodaysDate());
        addSemester();
    }

    private void assocCLassList() {
        // sets up recyclerview
        RecyclerView recyclerView = findViewById(R.id.courseViewRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assocClasses = repository.getAllClasses();
        classesInSemester = new ArrayList<>();
            for (Classes classes : assocClasses) {
                if (classes.getSemesterId() == semesterId) {
                    classesInSemester.add(classes);
                }
            }
            classAdapter.setClasses(classesInSemester);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    try {
                        DialogInterface.OnClickListener classDeleteClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Classes classes = classAdapter.getClass(viewHolder.getAdapterPosition());
                                        classesInSemester.remove(classes);
                                        classAdapter.notifyItemRemoved(which);
                                        classAdapter.setClasses(classesInSemester);
                                        overWriteClass(classes, -1);
                                        Toast.makeText(AddSemester.this, "Class has been removed from semester.", Toast.LENGTH_SHORT).show();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        classAdapter.notifyDataSetChanged();
                                        classAdapter.setClasses(classesInSemester);
                                        Toast.makeText(AddSemester.this, "Class has not been removed from semester.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(AddSemester.this);
                        alert.setMessage("Do you want to remove this class from this semester?").setPositiveButton("Yes", classDeleteClickListener)
                                .setNegativeButton("No", classDeleteClickListener).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).attachToRecyclerView(recyclerView);
        }

    private void addSemester() {
        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(v -> {
            semesterTitle = semesterTitleTxt.getText().toString();
            if (isNull()) {
                return;
            }
            startDate = startDatePickerButton.getText().toString();
            endDate = endDatePickerButton.getText().toString();

            if (semesterId == -1) {
                int newSemesterId = repository.getAllSemesters().get(repository.getAllSemesters().size() - 1).getSemesterId() + 1;
                semesters = new Semesters(newSemesterId, semesterTitle, startDate, endDate);
                repository.insert(semesters);
                Toast.makeText(AddSemester.this, "Semester Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddSemester.this, Home.class);
                startActivity(intent);
            } else {
                semesters = new Semesters(semesterId, semesterTitle, startDate, endDate);
                repository.update(semesters);
                Toast.makeText(AddSemester.this, "Semester has been updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddSemester.this, Home.class);
                startActivity(intent);
            }

        });
    }

    public boolean isNull() {
        if (semesterTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The semester must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getTodaysDate() == null) {
            Toast.makeText(this, "The semester must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getEndDate() == null) {
            Toast.makeText(this, "The semester must have an end date.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    public void addClassesToSemester(View view) {
        unassignedClasses = new ArrayList<>();
        for (Classes classes : assocClasses) {
            if (classes.getSemesterId() <= -1) {
                unassignedClasses.add(classes);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add A Class To This Semester");
        builder.setMessage("Do you want to select an existing class to add to this semester or create a new class for this semester?");
        builder.setIcon(R.drawable.ic_add_button);
        builder.setPositiveButton("New Class", (dialog, id) -> {
            dialog.dismiss();
            Intent intent = new Intent(AddSemester.this, AddClass.class);
            intent.putExtra("semesterId", semesterId);
            this.startActivity(intent);
        });
        builder.setNegativeButton("Existing Class",(dialog, id) -> {
            if (unassignedClasses.size() >= 1) {
                final ClassDropDownMenu classMenu = new ClassDropDownMenu(this, unassignedClasses);
                classMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                classMenu.setWidth(getPxFromDisplay(250));
                classMenu.setOutsideTouchable(true);
                classMenu.setFocusable(true);
                classMenu.showAsDropDown(addClassesButton);
                classMenu.setSelectedClassListener((position, classes) -> {
                    classesInSemester.add(classes);
                    classAdapter.notifyItemInserted(position);
                    classAdapter.setClasses(classesInSemester);
                    classMenu.dismiss();
                    classes.setSemesterId(semesterId);
                    overWriteClass(classes, semesterId);
                    Toast.makeText(getApplicationContext(), "Class has been assigned to this semester.", Toast.LENGTH_SHORT).show();
                });
            }else {
                Toast.makeText(getApplicationContext(), "There are no unassigned classes. Please create a new class to add to this semester.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void overWriteClass(Classes classes, int semesterId) {
        classes.setSemesterId(semesterId);
        repository.update(classes);
    }

    private int getPxFromDisplay(int dp) {
        return(int) (dp*getResources().getDisplayMetrics().density);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.semester_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.home:
                Intent intent = new Intent(AddSemester.this, Home.class);
                startActivity(intent);
                return true;

            case R.id.semesterStartAlert:
                editStartDate = startDatePickerButton.getText().toString();
                Date mStart = null;
                try {
                    mStart = formatter.parse(editStartDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long startTrigger = mStart.getTime();
                Intent addTermIntent = new Intent(AddSemester.this, MyBroadcastReceiver.class);
                addTermIntent.putExtra("key", "The Semester " + getIntent().getStringExtra("semesterName") + " starts today " + getIntent().getStringExtra("startDate"));
                PendingIntent startSender = PendingIntent.getBroadcast(AddSemester.this, (1240000 + semesterId), addTermIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, startTrigger, startSender);
                Toast.makeText(AddSemester.this, "Semester start date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.semesterEndAlert:
                editEndDate = endDatePickerButton.getText().toString();
                Date mEnd = null;
                try {
                    mEnd = formatter.parse(editEndDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTrigger = mEnd.getTime();
                Intent endTermIntent = new Intent(AddSemester.this, MyBroadcastReceiver.class);
                endTermIntent.putExtra("key", "The Semester " + getIntent().getStringExtra("semesterName") + " finishes today!");
                PendingIntent endSender = PendingIntent.getBroadcast(AddSemester.this, (1250000 + semesterId), endTermIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, endTrigger, endSender);
                Toast.makeText(AddSemester.this, "Semester end date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.deleteSemester:
                if (semesterId != -1) {
                    checkForClassesInThisSemester();
                }else {
                    Toast.makeText(AddSemester.this, "Cannot delete a semester that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
        }
        return false;
    }

    private boolean checkForClassesInThisSemester() {
        Semesters deleteSemester = new Semesters(semesterId, editSemesterTitle, editStartDate, editEndDate);
        if(classesInSemester.size() >= 1) {
            Toast.makeText(AddSemester.this, "Cannot delete semester with classes assigned.", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(AddSemester.this, "Deleted semester", Toast.LENGTH_SHORT).show();
            repository.delete(deleteSemester);
            Intent intent = new Intent(AddSemester.this, Home.class);
            startActivity(intent);
            return false;
        }
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        makeDateString(day, month, year);
        return makeDateString(day, month, year);

    }

    private String getEndDate()  {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        endDateString(day, month, year);
        return endDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String start = makeDateString(dayOfMonth, month, year);
            startDatePickerButton.setText(start);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String end = endDateString(dayOfMonth, month, year);
            endDatePickerButton.setText(end);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        endDatePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        month = month + 1;
        return month + "/" + dayOfMonth + "/" + year;
    }

    private String endDateString (int dayOfMonth, int month, int year) {
        month = month + 1;
        return month+ "/" + dayOfMonth + "/" + year;
    }

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    public void openEndDate(View view) {endDatePickerDialog.show(); }
}
