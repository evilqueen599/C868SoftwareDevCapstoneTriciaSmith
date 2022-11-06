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
        Users user = new Users(1, "Tricia", "Aloufi", "smithtricia91@gmail.com", "admin", "admin");
        repository.insert(user);
        Semesters semesters = new Semesters(1, "Initial Term","08/09/2020", "12/22/2020");
        repository.insert(semesters);
        Semesters semesters1 = new Semesters(2, "Second Term","01/06/2021", "04/28/2021");
        repository.insert(semesters1);
        Semesters semesters2 = new Semesters(3, "Third Term","05/04/2021", "07/31/2021");
        repository.insert(semesters2);
        Semesters semesters3 = new Semesters(4, "Fourth Term","08/07/2021", "12/23/2021");
        repository.insert(semesters3);
        Semesters semesters4 = new Semesters(5, "Fifth Term","01/08/2022", "04/30/2022");
        repository.insert(semesters4);
        Semesters semesters5 = new Semesters(6, "Final Term", "06/01/2022", "11/31/2022");
        repository.insert(semesters5);
        Classes classes = new Classes(1, "Application Development", "Tricia Aloufi",
                "testemail@test.com", "954-778-9008", "Completed","10/26/2022", "12/26/2022", "Finish this class ASAP", 5);
        repository.insert(classes);
        Classes classes1 = new Classes(2, "Software Development Capstone", "Candice Allen",
                "testemail@test.com", "954-778-9008", "In Progress","10/26/2022", "11/08/2022", "You made it!", 5);
        repository.insert(classes1);
        Classes classes2 = new Classes(3, "Software Engineering", "Tricia Aloufi",
                "testemail@test.com", "954-778-9008", "Completed","06/01/2022", "06/14/2022", "Study hard.", 5);
        repository.insert(classes2);
        Classes classes3 = new Classes(4, "User Experience Design", "Tricia Aloufi",
                "testemail@test.com", "954-778-9008", "Completed","06/15/2022", "06/28/2022", "This class isn't so bad.", 5);
        repository.insert(classes3);
        Assignments assignments = new Assignments(1, "Software II Project","07/26/2022", "08/28/2022", "Objective Assessment", 1);
        repository.insert(assignments);
        Assignments assignments1 = new Assignments(2, "Capstone Report","10/26/2022", "11/28/2023", "Summary Essay", 2);
        repository.insert(assignments1);
        Assignments assignments2 = new Assignments(3, "Capstone Project","10/26/2022", "11/28/2023", "Objective Assessment", 2);
        repository.insert(assignments2);
        Assignments assignments3 = new Assignments(4, "English Paper","10/26/2022", "11/28/2023", "Summary Essay", 3);
        repository.insert(assignments3);
        Assignments assignments4 = new Assignments(5, "Research Writing","10/26/2022", "11/28/2023", "Research Essay", 3);
        repository.insert(assignments4);
    }
    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}