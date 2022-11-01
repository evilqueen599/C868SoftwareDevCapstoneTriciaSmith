package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Update
    void update(Users users);

    @Delete
    void delete(Users users);

    @Query("SELECT * FROM users ORDER BY userId ASC")
    List<Users> getAllUsers();
}

