package com.orasi.bluesource.employeesPage;

import com.orasi.utils.dataHelpers.personFactory.Person;

public class Employee extends Person {
    private String employeeTitle;
    private String role;
    private String manager;
    private String status;
    private String bridgeTime;
    private String location;
    private String startDate;
    private String cellPhone;
    private String officePhone;
    private String imName;
    private String imClient;
    private String department;

    public Employee() {
        super();
        addPhone();
        getAllPhones().get(0).setType("Cell");
        this.employeeTitle = "Contractor";
        this.role = "Base";
        this.manager = "Company Admin";
        this.status = "Contractor";
        this.bridgeTime = "1";
        this.location = "Remote";
        this.startDate = "2013-01-20";
        this.cellPhone = getAllPhones().get(0).getFormattedNumber("(###) ###-####");
        this.officePhone = getAllPhones().get(1).getFormattedNumber("(###) ###-####");
        this.imName = primaryEmail().getEmail();
        this.imClient = "Skype";
        this.department = "Rural Testing";
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public String getRole() {
        return role;
    }

    public String getManager() {
        return manager;
    }

    public String getStatus() {
        return status;
    }

    public String getBridgeTime() {
        return bridgeTime;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    // public Date getStartDateAsDate() {return startDate;}
    public String getCellPhone() {
        return cellPhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public String getImName() {
        return imName;
    }

    public String getImClient() {
        return imClient;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBridgeTime(String bridgeTime) {
        this.bridgeTime = bridgeTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }

    public void setImClient(String imClient) {
        this.imClient = imClient;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
