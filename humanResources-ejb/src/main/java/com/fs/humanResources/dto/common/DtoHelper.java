package com.fs.humanResources.dto.common;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.model.employee.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class DtoHelper {

    public List<EmployeeDTO> getDTOList(List<Employee> employeeList) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeeList) {
            employeeDTOList.add(new EmployeeDTO(employee));
        }

        return employeeDTOList;
    }

    public static DtoHelper create() {
        return new DtoHelper();
    }
}
