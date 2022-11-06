package com.example.c868softwaredevcapstonetriciaaloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.c868softwaredevcapstonetriciaaloufi.Database.Database;
import com.example.c868softwaredevcapstonetriciaaloufi.Database.UserDAO;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;
import com.example.c868softwaredevcapstonetriciaaloufi.R;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView attempts;
    int counter = 3;

    String user;
    String pass;

    private UserDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        attempts = findViewById(R.id.attempts);

        Database database = Room.databaseBuilder(this, Database.class, "myDatabase.db")
                        .allowMainThreadQueries().build();
        db =database.userDAO();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                validate();
                if(validate() == false) {
                    counter --;
                    attempts.setText("Attempts remaining: " + String.valueOf(counter));
                    if (counter == 0) {
                        login.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "No login attempts left, try again later.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Username or password incorrect, please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean validate() {
        try {
            Users tempuser = db.getUser(username.getText().toString(), password.getText().toString());
            if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, " Please enter both username and password.", Toast.LENGTH_SHORT).show();
                return true;
            } else if (username.getText().toString().equals(tempuser.getUserName()) && password.getText().toString().equals(tempuser.getPassword())) {
                Toast.makeText(LoginActivity.this, "Sign in success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Home.class);
                startActivity(intent);
                return true;
            } else {
                Toast.makeText(LoginActivity.this, "Sign in failure", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NullPointerException e) {
            Toast.makeText(LoginActivity.this, "Username or password incorrect.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void registerANewUser(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
