package com.example.c868softwaredevcapstonetriciaaloufi.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c868softwaredevcapstonetriciaaloufi.Models.Users;

@androidx.room.Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();

    private static volatile Database INSTANCE;

    static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ((Database.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "myDatabase.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
