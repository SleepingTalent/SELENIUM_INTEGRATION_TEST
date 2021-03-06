package com.fs.humanResources.employee.model;

import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EmployeeModel implements Serializable {

    Logger log = Logger.getLogger(EmployeeModel.class);

    private EmployeeViewBean employee;

    public EmployeeViewBean getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewBean employee) {
        log.info("Setting "+employee+" in EmployeeModel");
        this.employee = employee;
    }

}