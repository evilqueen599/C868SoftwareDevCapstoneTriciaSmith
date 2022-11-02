package com.example.c868softwaredevcapstonetriciaaloufi.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "classes")
public class Classes {
    @PrimaryKey(autoGenerate = true)
    private int classId;

    @ColumnInfo
    private String className;

    @ColumnInfo
    private String instructorName;

    @ColumnInfo
    private String instructorEmail;

    @ColumnInfo
    private String instructorPhone;

    @ColumnInfo
    private String courseStatus;

    @ColumnInfo
    private String startDate;

    @ColumnInfo
    private String endDate;

    @ColumnInfo
    private String classNotes;

    @ColumnInfo
    private int SemesterId;

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", classNotes='" + classNotes + '\'' +
                ", SemesterId=" + SemesterId +
                '}';
    }

    public Classes(int classId, String className, String instructorName, String instructorEmail, String instructorPhone, String courseStatus, String startDate, String endDate, String classNotes, int semesterId) {
        this.classId = classId;
        this.className = className;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.courseStatus = courseStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classNotes = classNotes;
        SemesterId = semesterId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
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

    public String getClassNotes() {
        return classNotes;
    }

    public void setClassNotes(String classNotes) {
        this.classNotes = classNotes;
    }

    public int getSemesterId() {
        return SemesterId;
    }

    public void setSemesterId(int semesterId) {
        SemesterId = semesterId;
    }
}
