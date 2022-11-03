package com.example.c868softwaredevcapstonetriciaaloufi.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teachers")
public class Teachers {

    @PrimaryKey(autoGenerate = true)
    private int instructorId;
    @ColumnInfo
    private String instructorName;
    @ColumnInfo
    private String instructorPhone;
    @ColumnInfo
    private String instructorEmail;

    @Override
    public String toString() {
        return "Teachers{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                '}';
    }

    public Teachers(int instructorId, String instructorName, String instructorPhone, String instructorEmail) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
