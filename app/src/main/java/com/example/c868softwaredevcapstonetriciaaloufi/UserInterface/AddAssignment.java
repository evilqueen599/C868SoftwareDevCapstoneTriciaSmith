package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.MyBroadcastReceiver;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddAssignment extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog startPickerDialog;
    int assignmentId;
    int editClassId;
    String assignmentName;
    String editAssignmentName;
    String editAssignmentType;
    String editStartDate;
    String editEndDate;
    String startDate;
    String endDate;
    String assignmentType;
    Spinner assignmentTypeSelect;
    EditText assignmentNameTxt;
    Button endAssignPickerBtn;
    Button startAssignPickerBtn;
    Button addAssignBtn;
    Repository repository;
    Assignments assignments;
    SimpleDateFormat formatter;
    String format;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment_activity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        format = "MM/dd/yyyy";
        formatter = new SimpleDateFormat(format, Locale.US);
        repository = new Repository(getApplication());
        assignmentId = getIntent().getIntExtra("assignmentId", -1);
        editAssignmentName = getIntent().getStringExtra("assignmentTitle");
        editAssignmentType = getIntent().getStringExtra("assignmentType");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");
        editClassId = getIntent().getIntExtra("classId", -1);
        if (assignmentId == -1) {
            setUpView();
        } else {
            assignmentNameTxt = findViewById(R.id.assignmentNameTxt);
            assignmentNameTxt.setText(editAssignmentName);
            startAssignPickerBtn = findViewById(R.id.startAssignPickerButton);
            startAssignPickerBtn.setText(editStartDate);
            endAssignPickerBtn = findViewById(R.id.endAssignPickerButton);
            endAssignPickerBtn.setText(editEndDate);
            assignmentTypeSelect = findViewById(R.id.assignmentTypeBar);
            initDatePicker();
            initStartPicker();
            addNewAssessment();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assignmentSpinnerAdapter, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
            assignmentTypeSelect.setAdapter(adapter);
            if (editAssignmentType != null) {
                int spinnerPosition = adapter.getPosition(editAssignmentType);
                assignmentTypeSelect.setSelection(spinnerPosition);
            }
        }

    }

    private void setUpView() {
        assignmentNameTxt =findViewById(R.id.assignmentNameTxt);
        initDatePicker();
        initStartPicker();
        endAssignPickerBtn = findViewById(R.id.endAssignPickerButton);
        startAssignPickerBtn = findViewById(R.id.startAssignPickerButton);
        startAssignPickerBtn.setText(getTodaysDate());
        endAssignPickerBtn.setText(getTodaysDate());
        assignmentTypeSelect = findViewById(R.id.assignmentTypeBar);
        assignmentType = String.valueOf(assignmentTypeSelect.getSelectedItem());
        addNewAssessment();
    }

    private void addNewAssessment() {
        addAssignBtn = findViewById(R.id.addAssignBtn);
        addAssignBtn.setOnClickListener(v -> {
            assignmentName = assignmentNameTxt.getText().toString();
            startDate = startAssignPickerBtn.getText().toString();
            endDate = endAssignPickerBtn.getText().toString();
            assignmentType = assignmentTypeSelect.getSelectedItem().toString();
            if (isNull()) {
                return;
            }
            if (assignmentId == -1) {
                int newAssessId = repository.getAllAssignments().get(repository.getAllAssignments().size() - 1).getAssignmentId() + 1;
                assignments = new Assignments(newAssessId, assignmentName, startDate, endDate, assignmentType, editClassId);
                repository.insert(assignments);
                Toast.makeText(AddAssignment.this, "Assignment Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddAssignment.this, Home.class);
                startActivity(intent);
            } else {
                assignments = new Assignments(assignmentId, assignmentName, startDate, endDate, assignmentType, editClassId);
                repository.update(assignments);
                Toast.makeText(AddAssignment.this, "Assignment updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddAssignment.this, Home.class);
                startActivity(intent);
            }

        });
    }

    public boolean isNull() {
        if (assignmentNameTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The assignment must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (startDate == null) {
            Toast.makeText(this, "The assignment must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (endDate == null) {
            Toast.makeText(this, "The assignment must have a completion date.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (assignmentType == null) {
            Toast.makeText(this, "The assignment must have a type.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assign_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.home:
                Intent intent = new Intent(AddAssignment.this, Home.class);
                startActivity(intent);
                return true;

            case R.id.assign_start_date_notification:
                if(assignmentId != -1) {
                editStartDate = startAssignPickerBtn.getText().toString();
                Date mStart = null;
                try {
                    mStart = formatter.parse(editStartDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long startTrigger = mStart.getTime();
                Intent addAssessIntent = new Intent(AddAssignment.this, MyBroadcastReceiver.class);
                addAssessIntent.putExtra("key","The start date of Assignment " + getIntent().getStringExtra("assignmentTitle") + " is " + getIntent().getStringExtra("startDate") + ".");
                PendingIntent startSender=PendingIntent.getBroadcast(AddAssignment .this, (1260000 + assignmentId),addAssessIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,startTrigger,startSender);
                Toast.makeText(AddAssignment.this, "Assignment start date notification enabled.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddAssignment.this, "Cannot create an alert for an assignment that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
                return true;


            case R.id.assign_due_date_notification:
                if(assignmentId != -1) {
                editEndDate = endAssignPickerBtn.getText().toString();
                Date mEnd = null;
                try {
                    mEnd = formatter.parse(editEndDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTrigger = mEnd.getTime();
                Intent endAssessIntent=new Intent(AddAssignment.this, MyBroadcastReceiver.class);
                endAssessIntent.putExtra("key","The assignment " + getIntent().getStringExtra("assignmentTitle") + " is due today!");
                PendingIntent endSender=PendingIntent.getBroadcast(AddAssignment .this,(1270000 + assignmentId),endAssessIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,endTrigger,endSender);
                Toast.makeText(AddAssignment.this, "Assignment due date notification enabled.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddAssignment.this, "Cannot create an alert for an assignment that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.deleteAssignment:
                if (assignmentId != -1) {
                    Assignments deleteAssignment = new Assignments(assignmentId, editAssignmentName, editStartDate, editEndDate, editAssignmentType, editClassId);
                    Toast.makeText(AddAssignment.this, "Assignment deleted", Toast.LENGTH_SHORT).show();
                    repository.delete(deleteAssignment);
                    Intent intent1 = new Intent(AddAssignment.this, Home.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(AddAssignment.this, "Cannot delete an assignment that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        makeDateString(day, month, year);
        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String dueDate = makeDateString(dayOfMonth, month, year);
            endAssignPickerBtn.setText(dueDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private void initStartPicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String startDate = makeDateString(dayOfMonth, month, year);
            startAssignPickerBtn.setText(startDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        startPickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        month = month + 1;
        return month + "/" + dayOfMonth + "/" + year;
    }

    public void openDueDate(View view) {
        datePickerDialog.show();
    }
    public void openStartDate(View view) {
        startPickerDialog.show();
    }
}
