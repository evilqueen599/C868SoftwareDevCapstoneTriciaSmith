package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Assignments;
import com.example.c868softwaredevcapstonetriciaaloufi.Models.Teachers;

import java.util.List;

@Dao
public interface TeacherDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Teachers teachers);

    @Update
    void update (Teachers teachers);

    @Delete
    void delete (Teachers teachers);

    @Query("SELECT * FROM teachers ORDER BY instructorId ASC")
    List<Teachers> getAllTeachers();
}
