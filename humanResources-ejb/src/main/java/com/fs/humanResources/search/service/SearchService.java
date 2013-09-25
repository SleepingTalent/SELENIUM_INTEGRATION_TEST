package com.fs.humanResources.search.service;

import com.fs.humanResources.model.employee.entities.Employee;
import com.fs.humanResources.search.strategy.EmployeeSearchStrategy;
import com.fs.humanResources.search.strategy.SearchStrategy;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class SearchService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private SearchStrategy searchStrategy;

    @PostConstruct
    public void init() {
        searchStrategy = new EmployeeSearchStrategy(entityManager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Employee> performSearch(String searchTerm) {
        return searchStrategy.executeSearch(searchTerm);
    }
}
