package com.fs.humanResources.search.view;


import com.fs.humanResources.employee.view.employee.EmployeeViewBean;

public class SearchViewBean {

    private EmployeeViewBean employee;

    public SearchViewBean(EmployeeViewBean employee) {
        setEmployee(employee);
    }

    public EmployeeViewBean getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewBean employee) {
        this.employee = employee;
    }
}
