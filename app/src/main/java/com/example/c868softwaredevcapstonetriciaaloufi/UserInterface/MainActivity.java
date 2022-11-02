package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEST DATA TO HELP WITH INITIAL APP VIEW
        Repository repository = new Repository(getApplication());
        Users user = new Users(1, "Tricia", "Aloufi", "smithtricia91@gmail.com", "admin", "Admin1!");
        repository.insert(user);
        Semesters semesters = new Semesters(1, "Test Data","08/09/2022", "12/25/2022");
        repository.insert(semesters);
        Classes classes = new Classes(1, "Application Development", "Test Instructor",
                "testemail@test.com", "954-778-9008", "InProgress","10/26/2022", "12/26/2022", "Finish this class ASAP", 1);
        repository.insert(classes);
        Assignments assignments = new Assignments(1, "Final Project","10/26/2022", "11/28/2023", "Practice Assessment", 1);
        repository.insert(assignments);
    }
    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}