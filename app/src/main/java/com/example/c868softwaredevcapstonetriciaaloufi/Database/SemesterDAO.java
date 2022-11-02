package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Semesters;

import java.util.List;

@Dao
public interface SemesterDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Semesters semesters);

    @Update
    void update(Semesters semesters);

    @Delete
    void delete(Semesters semesters);

    @Query("SELECT * FROM semesters ORDER BY semesterId ASC")
    List<Semesters> getAllSemesters();

    @Query("SELECT * FROM semesters WHERE semesterId = :semesterId")
    Semesters getSemesterById(int semesterId);
}

