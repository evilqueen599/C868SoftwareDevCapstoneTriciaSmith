package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c868softwaredevcapstonetriciaaloufi.Database.Repository;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

public class RegisterActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText regEmailAddress;
    EditText userName;
    EditText passWord;
    Button registerBtn;
    int userId;
    Repository repository;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        repository = new Repository(getApplication());
        userId = getIntent().getIntExtra("userId", -1);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        regEmailAddress = findViewById(R.id.regEmailAddress);
        userName = findViewById(R.id.regUser);
        passWord = findViewById(R.id.regPass);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
