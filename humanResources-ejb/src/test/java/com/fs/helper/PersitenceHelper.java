package com.fs.helper;

import com.fs.humanResources.model.address.dao.AddressDAO;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class PersitenceHelper {

    private EntityManager entityManager;

    private EntityTransaction entityTransaction;

    public PersitenceHelper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PersitenceHelper(EntityManagerFactory entityManagerFactory) {
       this(entityManagerFactory.createEntityManager())  ;
    }

    public void beginTransaction() {
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    public void rollbackTransaction() {
        entityTransaction.rollback();
    }

    public void commitTransaction() {
        entityTransaction.commit();
    }

    public void closeSession() {
        if(null != entityManager) {
            entityManager.close();
        }
    }

    public Employee persistNewEmployee(Employee employee) {

        Assert.assertNull("Expected Id to be Null!",employee.getId());

        EmployeeDAO employeeDAO = new EmployeeDAO(entityManager);
        employeeDAO.create(employee);

        Assert.assertNotNull("Expected Id to be populated!",employee.getId());

        return employee;
    }

    public Address persistNewAddress(Address address, Employee employee) {
        address.setEmployee(employee);

        Assert.assertNull("Expected Id to be Null!",address.getId());

        AddressDAO addressDAO = new AddressDAO(entityManager);
        addressDAO.create(address);

        Assert.assertNotNull("Expected Id to be populated!",address.getId());

        return address;
    }
}
