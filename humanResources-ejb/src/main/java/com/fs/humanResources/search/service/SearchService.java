package com.fs.humanResources.search.service;

import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class SearchService implements Serializable {

    Logger log = Logger.getLogger(SearchService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private static String[] SEARCHABLE_FIELDS = {
            "id", "firstName", "lastName", "addressList.houseNumber", "addressList.postCode"};

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Employee> performSearch(String searchTerm) {
        log.info("Executing Search for : "+searchTerm);
        return executeSearch(searchTerm);
    }

    public List<Employee> executeSearch(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityMananger();

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Employee.class).get();

        Query query = queryBuilder.keyword()
                .onFields(SEARCHABLE_FIELDS)
                .matching(searchTerm).createQuery();

        List<Employee> results = createFullTextQuery(fullTextEntityManager, query).getResultList();
        log.info(results.size()+" results returned!");

        return results;
    }

    private FullTextEntityManager getFullTextEntityMananger() {
        return Search.getFullTextEntityManager(entityManager);
    }

    private javax.persistence.Query createFullTextQuery(FullTextEntityManager fullTextEntityManager, org.apache.lucene.search.Query query) {
        return fullTextEntityManager.createFullTextQuery(query, Employee.class);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
