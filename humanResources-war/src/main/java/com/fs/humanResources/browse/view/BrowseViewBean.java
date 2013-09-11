package com.fs.humanResources.browse.view;


import com.fs.humanResources.employee.view.employee.EmployeeViewBean;

public class BrowseViewBean {

    private EmployeeViewBean employee;

    public BrowseViewBean(EmployeeViewBean employee) {
        setEmployee(employee);
    }

    public EmployeeViewBean getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewBean employee) {
        this.employee = employee;
    }
}
