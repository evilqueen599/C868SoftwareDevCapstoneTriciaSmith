package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import android.app.Application;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
private UserDAO mUserDAO;
private SemesterDAO mSemesterDAO;
private ClassesDAO mClassesDAO;
private AssignmentDAO mAssignmentsDAO;

private List<Users> mAllUsers;
private List<Semesters> mAllSemesters;
private List<Classes> mAllClasses;
private List<Assignments> mAllAssignments;


private static int NUMBER_OF_THREADS = 12;
static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

public Repository(Application application) {
    Database db = Database.getDatabase(application);
    mUserDAO = db.userDAO();
}
//Users//
public List<Users> getAllUsers() {
    databaseExecutor.execute(() -> {
        mAllUsers = mUserDAO.getAllUsers();
    });
    try {
        Thread.sleep(1000);
    }catch (InterruptedException e) {
        e.printStackTrace();
    }return mAllUsers;
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
    //Semesters//
    public List<Semesters> getAllSemesters() {
        databaseExecutor.execute(() -> {
            mAllSemesters = mSemesterDAO.getAllSemesters();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }return mAllSemesters;
    }
    public void insert(Semesters semesters) {
        databaseExecutor.execute(() -> {
            mSemesterDAO.insert(semesters);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Semesters semesters) {
        databaseExecutor.execute(() -> {
            mSemesterDAO.update(semesters);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Semesters semesters) {
        databaseExecutor.execute(() -> {
            mSemesterDAO.delete(semesters);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Classes//

    public List<Classes> getAllClasses() {
        databaseExecutor.execute(() -> {
            mAllClasses = mClassesDAO.getAllClasses();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }return mAllClasses;
    }
    public void insert(Classes classes) {
        databaseExecutor.execute(() -> {
            mClassesDAO.insert(classes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Classes classes) {
        databaseExecutor.execute(() -> {
            mClassesDAO.update(classes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Classes classes) {
        databaseExecutor.execute(() -> {
            mClassesDAO.delete(classes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Assignments//

    public List<Assignments> getAllAssignments() {
        databaseExecutor.execute(() -> {
            mAllAssignments = mAssignmentsDAO.getAllAssignments();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }return mAllAssignments;
    }
    public void insert(Assignments assignments) {
        databaseExecutor.execute(() -> {
            mAssignmentsDAO.insert(assignments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assignments assignments) {
        databaseExecutor.execute(() -> {
            mAssignmentsDAO.update(assignments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assignments assignments) {
        databaseExecutor.execute(() -> {
            mAssignmentsDAO.delete(assignments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
