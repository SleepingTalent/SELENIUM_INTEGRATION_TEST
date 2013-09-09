package com.fs.humanResources.employee.controller;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import com.fs.humanResources.view.helper.FacesHelper;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
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

        log.info("Saving : " + employeeDTO);
        humanResourcesService.saveEmployeeDetails(employeeDTO);
    }

    public void updateEmployee() {
        EmployeeDTO employeeDTO = employeeModel.getEmployee().getDTO();

        log.info("Updating : " + employeeDTO);
        humanResourcesService.updateEmployeeDetails(employeeDTO);
    }

    public void findEmployee() {
        log.info("Find Employee with Id : " + getEmployee().getEmployeeId());

        try {

            EmployeeDTO employeeDTO = humanResourcesService.getEmployeeDetails(getEmployee().getEmployeeId());

            log.info(employeeDTO + " returned");
            employeeModel.setEmployee(new EmployeeViewBean(employeeDTO));

        } catch (EJBException ene) {
            log.error("Employee Id (" + employeeModel.getEmployee().getEmployeeId() + ") not found!");
            FacesHelper helper = new FacesHelper();
            helper.addErrorMessage("Employee Id (" + employeeModel.getEmployee().getEmployeeId() + ") not found!","");
            helper.failValidation();
        }
    }
}
