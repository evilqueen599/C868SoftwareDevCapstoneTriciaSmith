package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Classes;

import java.util.List;

@Dao
public interface ClassesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Classes classes);

    @Update
    void update (Classes classes);

    @Delete
    void delete (Classes classes);

    @Query("SELECT * FROM classes ORDER BY classId ASC")
    List<Classes> getAllClasses();
}
