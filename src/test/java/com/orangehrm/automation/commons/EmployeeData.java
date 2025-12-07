package com.orangehrm.automation.commons;

public class EmployeeData {
    private final String firstName;
    private final String lastName;
    private final String employeeId;
    private final String photoPath;

    public EmployeeData(String firstName, String lastName, String employeeId, String photoPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.photoPath = photoPath;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getEmployeeId() {

        return employeeId;
    }

    public String getPhotoPath() {

        return photoPath;
    }

    public String fullName() {

        return String.format("%s %s", firstName, lastName).trim();
    }
}
