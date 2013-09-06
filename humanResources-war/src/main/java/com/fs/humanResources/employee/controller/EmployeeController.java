package com.fs.humanResources.employee.controller;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EmployeeController {

    Logger log = Logger.getLogger(EmployeeController.class);

    @Inject
    EmployeeModel employeeModel;

    @Inject
    HumanResourcesService humanResourcesService;

    public EmployeeViewBean getEmployee() {
        return employeeModel.getEmployee();
    }

    public void clearEmployee() {
        employeeModel.setEmployee(new EmployeeViewBean());
    }

    public void saveEmployee() {
        EmployeeDTO employeeDTO = employeeModel.getEmployee().getDTO();
        humanResourcesService.saveEmployeeDetails(employeeDTO);
    }

    public void updateEmployee() {
        EmployeeDTO employeeDTO = employeeModel.getEmployee().getDTO();
        humanResourcesService.updateEmployeeDetails(employeeDTO);
    }

    public void findEmployee() {
        log.info("Find Employee with Id : "+getEmployee().getEmployeeId());

        try {

            EmployeeDTO employeeDTO = humanResourcesService.getEmployeeDetails(getEmployee().getEmployeeId());

            log.info(employeeDTO+" returned");
            employeeModel.setEmployee(new EmployeeViewBean(employeeDTO));

        } catch (EmployeeNotFoundException e) {

            log.error("Employee not found!");
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Find Error", "Employee Id (" + employeeModel.getEmployee().getEmployeeId() + ") not found!"));

        }
    }
}
