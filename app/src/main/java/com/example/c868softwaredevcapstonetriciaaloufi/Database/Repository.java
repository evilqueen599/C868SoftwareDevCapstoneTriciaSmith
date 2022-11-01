package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import android.app.Application;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
private UserDAO mUserDAO;

private List<Users> mallUsers;

private static int NUMBER_OF_THREADS = 12;
static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

public Repository(Application application) {
    Database db = Database.getDatabase(application);
    mUserDAO = db.userDAO();
}

public List<Users> getAllUsers() {
    databaseExecutor.execute(() -> {
        mallUsers = mUserDAO.getAllUsers();
    });
    try {
        Thread.sleep(1000);
    }catch (InterruptedException e) {
        e.printStackTrace();
    }return mallUsers;
}
    public void insert(Users users) {
        databaseExecutor.execute(() -> {
            mUserDAO.insert(users);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Users users) {
        databaseExecutor.execute(() -> {
            mUserDAO.update(users);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Users users) {
        databaseExecutor.execute(() -> {
            mUserDAO.delete(users);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
