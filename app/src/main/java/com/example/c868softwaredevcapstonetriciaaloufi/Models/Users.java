package com.example.c868softwaredevcapstonetriciaaloufi.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

    @Entity(tableName = "users")
    public class Users implements Serializable {
        @PrimaryKey(autoGenerate = true)
        public int userId;
        @ColumnInfo
        public String firstName;
        @ColumnInfo
        public String lastName;
        @ColumnInfo
        public String emailAddress;
        @ColumnInfo
        public String userName;
        @ColumnInfo
        public String password;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Users{" +
                    "userId=" + userId +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", emailAddress='" + emailAddress + '\'' +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }

        public Users(int userId, String firstName, String lastName, String emailAddress, String userName, String password) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
            this.userName = userName;
            this.password = password;
        }
    }
