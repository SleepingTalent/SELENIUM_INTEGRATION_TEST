package com.fs.humanResources.service;

import com.fs.humanResources.dto.common.DtoHelper;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.dto.search.SearchResultsDTO;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;
import com.fs.humanResources.model.employee.entities.Employee;
import com.fs.humanResources.search.service.SearchService;
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

    @Inject
    SearchService searchService;

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
        return DtoHelper.create().getDTOList(employeeDAO.findAll(first, pageSize));
    }

    public SearchResultsDTO searchForEmployees(String searchTerm, int first, int pageSize) {
        log.info("Search For Employees with searchTerm : " + searchTerm);
        return searchService.performSearch(searchTerm,first,pageSize);
    }

    public void saveEmployeeDetails(EmployeeDTO employeeDTO) {
        employeeDAO.create(employeeDTO.getEntity());
    }

    public void updateEmployeeDetails(EmployeeDTO employeeDTO) {
        employeeDAO.update(employeeDTO.getEntity());
    }

    public void deleteEmployeeDetails(EmployeeDTO employeeDTO) {
        employeeDAO.delete(employeeDTO.getEntity());
    }

    public int findTotalEmployeeCount() {
        return (int) employeeDAO.countAll();
    }

}
