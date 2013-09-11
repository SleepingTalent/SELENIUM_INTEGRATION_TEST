package com.fs.humanResources.model.employee.dao;

import com.fs.humanResources.model.common.dao.BaseDAO;
import com.fs.humanResources.model.employee.entities.Employee;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;


@Named
public class EmployeeDAO extends BaseDAO<Employee> {

    private static final String FIND_EMPLOYEE_BY_LASTNAME = "select e from Employee e where e.lastName = :thisLastName";

    public EmployeeDAO() {
        super(Employee.class);
    }

    public EmployeeDAO(EntityManager entityManager) {
        super(Employee.class, entityManager);
    }

    public Employee getEmployeeDetails(Long employeeId) throws NoResultException {
        return findById(employeeId);
    }

    public List<Employee> getEmployeesByLastname(String lastname) throws NoResultException{

        Query query = entityManager.createQuery(FIND_EMPLOYEE_BY_LASTNAME);
        query.setParameter("thisLastName",lastname);
        List<Employee> resultList = query.getResultList();

        if(resultList == null || resultList.size() == 0) {
            throw new NoResultException("No Results found for "+lastname);
        }

        return resultList;
    }

}
