package com.example.gpgondia;

public class Student {
    public String name, enrollment, scheme, dob, mobile, email, branch, year,password;

    public Student(String name, String enrollment, String scheme, String dob, String mobile, String email, String branch, String year,String password) {
        this.name = name;
        this.enrollment = enrollment;
        this.scheme = scheme;
        this.dob = dob;
        this.mobile = mobile;
        this.email = email;
        this.branch = branch;
        this.year = year;
        this.password=password;
    }
    // Getters
    public String getName() {
        return name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String getBranch() {
        return branch;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}

