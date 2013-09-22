package com.fs.humanResources.search.strategy;

import com.fs.humanResources.model.employee.entities.Employee;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchStrategy implements SearchStrategy<Employee> {

    private EntityManager entityManager;

    private static String[] SEARCHABLE_FIELDS = {
            "id", "firstName", "lastName", "addressList.houseNumber", "addressList.postCode"};

    public EmployeeSearchStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> executeSearch(String searchTerm) {
        return new ArrayList<Employee>();
    }
}
