package com.fs.humanResources.service;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;
import com.fs.humanResources.model.employee.entities.Employee;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateless
public class HumanResourcesService implements Serializable {

    Logger log = Logger.getLogger(HumanResourcesService.class);

    @Inject
    EmployeeDAO employeeDAO;

    public EmployeeDTO getEmployeeDetails(Long employeeId) throws EmployeeNotFoundException {
        try {
            log.info("Getting Employee Details for EmployeeId :" + employeeId);
            Employee employee = employeeDAO.getEmployeeDetails(employeeId);
            return new EmployeeDTO(employee);

        } catch (NoResultException e) {
            throw new EmployeeNotFoundException(e);
        }
    }

    public List<EmployeeDTO> findEmployees(int first, int pageSize) {
        log.info("Getting Employees " + first + " to " + pageSize);

        List<EmployeeDTO> employeeDTOList = new ArrayList();
        List<Employee> employeeList = employeeDAO.findAll(first, pageSize);

        for (Employee employee : employeeList) {
            employeeDTOList.add(new EmployeeDTO(employee));
        }

        return employeeDTOList;
    }

    public void saveEmployeeDetails(EmployeeDTO employeeDTO) {
        employeeDAO.create(employeeDTO.getEntity());
    }

    public void updateEmployeeDetails(EmployeeDTO employeeDTO) {
        employeeDAO.update(employeeDTO.getEntity());
    }

}
