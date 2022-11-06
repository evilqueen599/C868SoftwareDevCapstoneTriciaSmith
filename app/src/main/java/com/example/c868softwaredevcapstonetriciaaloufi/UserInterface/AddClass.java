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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868softwaredevcapstonetriciaaloufi.Adapters.ClassAssignmentAdapter;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
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

public class AddClass extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private Button startDateButton;

    private Button endDateButton;

    private Button addClassBtn;

    private FloatingActionButton addAssignmentsBtn;

    private int classId;

    private int editSemesterId;

    private Spinner classStatusBar;

    private EditText classTitleTxt;

    private EditText classNoteTxt;

    private EditText classInstructorTxt;

    private EditText instructorPhoneTxt;

    private EditText instructorEmailAddressTxt;

    private String classStatus;

    private String startDate;

    private String endDate;

    private String className;

    private String instructorName;

    private String instructorEmail;

    private String instructorPhone;

    private String classNote;

    private String editClassTitleTxt;

    private String editClassNoteTxt;

    private String editInstructorTxt;

    private String editInstructorPhoneTxt;

    private String editInstructorEmailTxt;

    private String editClassStatus;

    private String editEndDate;

    private String editStartDate;

    Repository repository;

    Classes classes;

    ClassAssignmentAdapter assessmentAdapter;

    List<Assignments> assocAssess;

    List<Assignments> assessInCourse;

    List<Assignments> unassignedAssessments;

    SimpleDateFormat formatter;

    String format;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class_activity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        format = "MM/dd/yyyy";
        formatter = new SimpleDateFormat(format, Locale.US);
        assessmentAdapter = new ClassAssignmentAdapter(this);
        repository = new Repository(getApplication());
        addAssignmentsBtn = findViewById(R.id.addAssignmentsBtn);
        classId = getIntent().getIntExtra("classId", -1);
        editClassTitleTxt = getIntent().getStringExtra("className");
        editInstructorTxt = getIntent().getStringExtra("instructorName");
        editInstructorPhoneTxt = getIntent().getStringExtra("instructorPhone");
        editInstructorEmailTxt = getIntent().getStringExtra("instructorEmail");
        editClassNoteTxt = getIntent().getStringExtra("classNote");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");
        editClassStatus = getIntent().getStringExtra("classStatus");
        editSemesterId = getIntent().getIntExtra("semesterId", -1);


        if (classId == -1) {
            setUpView();
        } else {
            initDatePicker();
            initEndDatePicker();
            startDateButton = findViewById(R.id.startDateButton);
            startDateButton.setText(editStartDate);
            endDateButton = findViewById(R.id.endDateButton);
            endDateButton.setText(editEndDate);
            classTitleTxt = findViewById(R.id.classTitleTxt);
            classTitleTxt.setText(editClassTitleTxt);
            classNoteTxt = findViewById(R.id.classNoteTxt);
            classNoteTxt.setText(editClassNoteTxt);
            instructorPhoneTxt = findViewById(R.id.instructorPhoneTxt);
            instructorPhoneTxt.setText(editInstructorPhoneTxt);
            instructorEmailAddressTxt = findViewById(R.id.instructorEmailAddressTxt);
            instructorEmailAddressTxt.setText(editInstructorEmailTxt);
            classInstructorTxt = findViewById(R.id.classInstructorTxt);
            classInstructorTxt.setText(editInstructorTxt);
            classStatusBar = findViewById(R.id.classStatusBar);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerAdapter, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            classStatusBar.setAdapter(adapter);
            if (editClassStatus != null) {
                int spinnerPosition = adapter.getPosition(editClassStatus);
                classStatusBar.setSelection(spinnerPosition);
            }
            addClass();
            assocAssessments();
        }

    }

    private void setUpView() {
        initDatePicker();
        initEndDatePicker();
        addAssignmentsBtn.hide();
        startDateButton = findViewById(R.id.startDateButton);
        startDateButton.setText(getTodaysDate());
        endDateButton = findViewById(R.id.endDateButton);
        endDateButton.setText(getEndDate());
        classTitleTxt = findViewById(R.id.classTitleTxt);
        classNoteTxt = findViewById(R.id.classNoteTxt);
        instructorPhoneTxt = findViewById(R.id.instructorPhoneTxt);
        instructorEmailAddressTxt = findViewById(R.id.instructorEmailAddressTxt);
        classInstructorTxt = findViewById(R.id.classInstructorTxt);
        classStatusBar = findViewById(R.id.classStatusBar);
        classStatus = String.valueOf(classStatusBar.getSelectedItem());
        addClass();
    }

    private void assocAssessments() {
        RecyclerView recyclerView = findViewById(R.id.assessViewRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assocAssess = repository.getAllAssignments();
        assessInCourse = new ArrayList<>();
        for (Assignments assessments : assocAssess) {
            if (assessments.getClassId() == classId) {
                assessInCourse.add(assessments);
            }
        }
        assessmentAdapter.setAssignments(assessInCourse);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    DialogInterface.OnClickListener assessDeleteClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Assignments assignments = assessmentAdapter.getAssignment(viewHolder.getAdapterPosition());
                                    assessInCourse.remove(assignments);
                                    assessmentAdapter.notifyItemRemoved(which);
                                    assessmentAdapter.setAssignments(assessInCourse);
                                    overWriteAssignment(assignments, -1);
                                    Toast.makeText(AddClass.this, "Assignment has been removed from this class.", Toast.LENGTH_SHORT).show();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    assessmentAdapter.notifyDataSetChanged();
                                    Toast.makeText(AddClass.this, "Assignment has not been removed from this course.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(AddClass.this);
                    alert.setMessage("Do you want to remove this assignment from this class?").setPositiveButton("Yes", assessDeleteClickListener)
                            .setNegativeButton("No", assessDeleteClickListener).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void addClass() {
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener( v -> {

            className = classTitleTxt.getText().toString();
            instructorName = classInstructorTxt.getText().toString();
            instructorEmail = instructorEmailAddressTxt.getText().toString();
            instructorPhone = instructorPhoneTxt.getText().toString();
            classStatus = classStatusBar.getSelectedItem().toString();
            startDate = startDateButton.getText().toString();
            endDate = endDateButton.getText().toString();
            classNote = classNoteTxt.getText().toString();

            if (isNull()) {
                return;
            }
            if (classId == -1) {
                int newCourseId = repository.getAllClasses().get(repository.getAllClasses().size() - 1).getClassId() +1;
                classes = new Classes(newCourseId, className, instructorName, instructorEmail, instructorPhone, classStatus, startDate, endDate, classNote, editSemesterId);
                repository.insert(classes);
                Toast.makeText(AddClass.this, "Class Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddClass.this, Home.class);
                startActivity(intent);
            }else {
                classes = new Classes(classId, className, instructorName, instructorEmail, instructorPhone, classStatus, startDate, endDate, classNote, editSemesterId);
                repository.update(classes);
                Toast.makeText(AddClass.this, "Class has been updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddClass.this, Home.class);
                startActivity(intent);
            }
        });
    }
    private boolean isNull() {
        if (classTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The class must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (startDate == null) {
            Toast.makeText(this, "The class must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (endDate == null) {
            Toast.makeText(this, "The class must have a completion date.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (classInstructorTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The class must have an Instructor.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorPhoneTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The class must have an Instructor phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorEmailAddressTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The class must have an Instructor email address.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (classStatusBar == null) {
            Toast.makeText(this, "The class must have a status.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    public void addAssignmentToClass(View view) {
        unassignedAssessments = new ArrayList<>();
        for (Assignments assignments : assocAssess) {
            if (assignments.getClassId() <= -1) {
                unassignedAssessments.add(assignments);
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add An Assignment To This Class");
        builder.setMessage("Do you want to select an existing assignment to add to this class or create a new assignment for this class?");
        builder.setIcon(R.drawable.ic_add_button);
        builder.setPositiveButton("New Assignment",(dialog, id) -> {
                dialog.dismiss();
                Intent intent = new Intent(this, AddAssignment.class);
                intent.putExtra("classId", classId);
                this.startActivity(intent);
            });
        builder.setNegativeButton("Existing Assessment", (dialog, id) -> {
                if (unassignedAssessments.size() >= 1) {
                    final AssignDropDownMenu assessMenu = new AssignDropDownMenu(this, unassignedAssessments);
                    assessMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                    assessMenu.setWidth(getPxFromDisplay(250));
                    assessMenu.setOutsideTouchable(true);
                    assessMenu.setFocusable(true);
                    assessMenu.showAsDropDown(addAssignmentsBtn);
                    assessMenu.setSelectedAssessListener((position, assignments) -> {
                        assessInCourse.add(assignments);
                        assessmentAdapter.notifyItemInserted(position);
                        assessmentAdapter.setAssignments(assessInCourse);
                        assessMenu.dismiss();
                        assignments.setClassId(classId);
                        overWriteAssignment(assignments, classId);
                        Toast.makeText(getApplicationContext(), "Assignment has been assigned to this class.", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "There are no unassigned assignments. Please create a new assignment to add to this class.", Toast.LENGTH_SHORT).show();
                }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void overWriteAssignment(Assignments assignments, int classId) {
        assignments.setClassId(classId);
        repository.update(assignments);
    }

    private int getPxFromDisplay(int dp) {
        return(int) (dp*getResources().getDisplayMetrics().density);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent intent = new Intent(AddClass.this, Home.class);
                startActivity(intent);
                return true;

            case R.id.startDateAlert:
                if(classId != -1) {
                editStartDate = startDateButton.getText().toString();
                Date mStart = null;
                try {
                    mStart = formatter.parse(editStartDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long startTrigger = mStart.getTime();
                Intent addCourseIntent=new Intent(AddClass.this, MyBroadcastReceiver.class);
                addCourseIntent.putExtra("key","The Class " + getIntent().getStringExtra("className") + " begins today " + getIntent().getStringExtra("startDate"));
                PendingIntent startSender=PendingIntent.getBroadcast(AddClass .this, (1280000 + classId),addCourseIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,startTrigger,startSender);
                Toast.makeText(AddClass.this, "Class start date notification enabled.", Toast.LENGTH_SHORT).show();
                }else {
                Toast.makeText(AddClass.this, "Cannot create an alert for a class that doesn't exist.", Toast.LENGTH_SHORT).show();
                 }
                return true;


            case R.id.endDateAlert:
                if (classId != -1) {
                editEndDate = endDateButton.getText().toString();
                Date mEnd = null;
                try {
                    mEnd = formatter.parse(editEndDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTrigger = mEnd.getTime();
                Intent endIntent=new Intent(AddClass.this, MyBroadcastReceiver.class);
                endIntent.putExtra("key","The Class " + getIntent().getStringExtra("className") + " finishes today!");
                PendingIntent endSender=PendingIntent.getBroadcast(AddClass .this,(1290000 + classId),endIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,endTrigger,endSender);
                Toast.makeText(AddClass.this, "Class due date notification enabled.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddClass.this, "Cannot create an alert for a class that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.shareClassNotes:
                if (classId != -1) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, (classNoteTxt.getText().toString()));
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Notes For " + classTitleTxt.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                }else {
                    Toast.makeText(AddClass.this, "Cannot share a note for a class that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.generateClassReport:
                Intent intent1 = new Intent(AddClass.this, ClassStatusReport.class);
                startActivity(intent1);
                return true;

            case R.id.deleteClass:
                if (classId != -1) {
                    checkForAssignmentsInThisClass();
                }else {
                    Toast.makeText(AddClass.this, "Cannot delete a class that doesn't exist.", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private boolean checkForAssignmentsInThisClass() {
        Classes deleteClass = new Classes(classId, editClassTitleTxt, editInstructorTxt, editInstructorEmailTxt, editInstructorPhoneTxt, editClassStatus, editStartDate, editEndDate, editClassNoteTxt, editSemesterId);
        if(assessInCourse.size() >= 1) {
            Toast.makeText(AddClass.this, "Cannot delete class with assignments assigned.", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(AddClass.this, "Deleted class", Toast.LENGTH_SHORT).show();
            repository.delete(deleteClass);
            Intent intent = new Intent(AddClass.this, Home.class);
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
            String startDate = makeDateString(dayOfMonth, month, year);
            startDateButton.setText(startDate);
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
            String endDate = endDateString(dayOfMonth, month, year);
            endDateButton.setText(endDate);
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

    public void openEndDate(View view) {
        endDatePickerDialog.show();
    }
}
