package com.example.c868softwaredevcapstonetriciaaloufi.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignments")
public class Assignments {
    @PrimaryKey (autoGenerate = true)
    private int assignmentId;

    @ColumnInfo
    private String assignmentTitle;

    @ColumnInfo
    private String startDate;

    @ColumnInfo
    private String endDate;

    @ColumnInfo
    private String assignmentType;

    @ColumnInfo
    private int classId;

    @Override
    public String toString() {
        return "Assignments{" +
                "assignmentId=" + assignmentId +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", assignmentType='" + assignmentType + '\'' +
                ", classId=" + classId +
                '}';
    }

    public Assignments(int assignmentId, String assignmentTitle, String startDate, String endDate, String assignmentType, int classId) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignmentType = assignmentType;
        this.classId = classId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
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

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
