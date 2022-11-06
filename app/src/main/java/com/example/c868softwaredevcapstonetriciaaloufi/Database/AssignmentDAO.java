package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Assignments assignments);

    @Update
    void update (Assignments assignments);

    @Delete
    void delete (Assignments assignments);

    @Query("SELECT * FROM assignments ORDER BY assignmentId ASC")
    List<Assignments> getAllAssignments();
}
