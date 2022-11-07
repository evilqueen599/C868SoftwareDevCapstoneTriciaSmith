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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                validator();
                if (validator() == true) {
                    String firstname = firstName.getText().toString().trim();
                    String lastname = lastName.getText().toString().trim();
                    String email = regEmailAddress.getText().toString().trim();
                    String username = userName.getText().toString().trim();
                    String password = passWord.getText().toString().trim();
                    int newId = repository.getAllUsers().get(repository.getAllUsers().size() - 1).getUserId() + 1;
                    users = new Users(newId, firstname, lastname, email, username, password);
                    repository.insert(users);
                    Toast.makeText(RegisterActivity.this, "Registration Successful Please Login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean validator() {
        String firstname = firstName.getText().toString().trim();
        String lastname = lastName.getText().toString().trim();
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(regEmailAddress.getText().toString());
        String email = regEmailAddress.getText().toString().trim();
        String username = userName.getText().toString().trim();
        String password = passWord.getText().toString().trim();
        if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!matcher.find()) {
            Toast.makeText(RegisterActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userId == -1) {
            return true;
        }
        return false;
    }
}
