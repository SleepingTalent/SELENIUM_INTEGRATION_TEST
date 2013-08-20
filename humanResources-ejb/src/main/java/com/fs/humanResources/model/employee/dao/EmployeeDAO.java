package com.fs.humanResources.model.employee.dao;

import com.fs.humanResources.model.common.dao.BaseDAO;
import com.fs.humanResources.model.employee.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class EmployeeDAO extends BaseDAO<Employee> {

    private static final String FIND_EMPLOYEE_DETAILS = "select e from Employee e where e.staffNumber = :thisStaffNumber";

    public EmployeeDAO() {
        super(Employee.class);
    }

    public EmployeeDAO(EntityManager entityManager) {
        super(Employee.class, entityManager);
    }

    public Employee getEmployeeDetails(Long staffNumber) throws NoResultException {
        Query query = entityManager.createQuery(FIND_EMPLOYEE_DETAILS);
        query.setParameter("thisStaffNumber",staffNumber);
        return (Employee) query.getSingleResult();
    }
}
