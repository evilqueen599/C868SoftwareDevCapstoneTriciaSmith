package com.example.c868softwaredevcapstonetriciaaloufi.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "semesters")
public class Semesters {
    @PrimaryKey(autoGenerate = true)
    private int semesterId;

    @ColumnInfo
    private String semesterName;

    @ColumnInfo
    private String startDate;

    @ColumnInfo
    private String endDate;

    @Override
    public String toString() {
        return "Semesters{" +
                "semesterId=" + semesterId +
                ", semesterName='" + semesterName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Semesters(int semesterId, String semesterName, String startDate, String endDate) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
