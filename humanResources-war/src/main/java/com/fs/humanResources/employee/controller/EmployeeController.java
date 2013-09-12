package com.fs.humanResources.employee.controller;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.view.dialog.Dialogs;
import com.fs.humanResources.view.helper.ViewHelper;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EmployeeController {

    Logger log = Logger.getLogger(EmployeeController.class);

    @Inject
    private EmployeeModel employeeModel;

    @Inject
    private HumanResourcesService humanResourcesService;

    protected ViewHelper viewHelper;

    @PostConstruct
    public void postContruct() {
        viewHelper = new ViewHelper();
    }

    public EmployeeViewBean getEmployee() {
        return employeeModel.getEmployee();
    }

    public void clearEmployee() {
        employeeModel.setEmployee(new EmployeeViewBean());
    }

    public void saveEmployee() {
        EmployeeDTO employeeDTO = employeeModel.getEmployee().getDTO();

        try {
            log.info("Saving : " + employeeDTO);
            humanResourcesService.saveEmployeeDetails(employeeDTO);
        } catch (EJBException ejbe) {
            handleErrors("Error Saving : " + employeeDTO.getFirstName()+" "+employeeDTO.getLastName());
            throw new AbortProcessingException();
        }
    }

    public void updateEmployee() {
        EmployeeDTO employeeDTO = employeeModel.getEmployee().getDTO();

        try {
            log.info("Updating : " + employeeDTO);
            humanResourcesService.updateEmployeeDetails(employeeDTO);
        } catch (EJBException ejbe) {
            handleErrors("Error Updating : " + employeeDTO.getFirstName()+" "+employeeDTO.getLastName());
            throw new AbortProcessingException();
        }
    }

    public void findEmployee() {
        log.info("Find Employee with Id : " + getEmployee().getEmployeeId());

        try {
            EmployeeDTO employeeDTO = humanResourcesService.getEmployeeDetails(getEmployee().getEmployeeId());

            log.info(employeeDTO + " returned");
            employeeModel.setEmployee(new EmployeeViewBean(employeeDTO));
        } catch (EJBException ejbe) {
            handleErrors("Employee Id (" + employeeModel.getEmployee().getEmployeeId() + ") not found!");
            throw new AbortProcessingException();
        }
    }

    private void handleErrors(String message) {
        log.error(message);
        viewHelper.addErrorMessage(message, "");
        viewHelper.failValidation();
    }
}
