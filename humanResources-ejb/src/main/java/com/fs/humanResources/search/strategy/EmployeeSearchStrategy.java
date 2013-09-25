package com.fs.humanResources.search.strategy;

import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeSearchStrategy implements SearchStrategy<Employee> {

    private EntityManager entityManager;

    private FullTextEntityManager fullTextEntityManager;

    private static String[] SEARCHABLE_FIELDS = {
            "id", "firstName", "lastName",
            "addressList.houseNumber", "addressList.postCode"};

    public EmployeeSearchStrategy(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.fullTextEntityManager = getFullTextEntityMananger();
    }

    @Override
    public List<Employee> executeSearch(String searchTerm) {
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Employee.class).get();

        Query query = queryBuilder.keyword().onFields(SEARCHABLE_FIELDS)
                .matching(searchTerm).createQuery();

        return createFullTextQuery(fullTextEntityManager, query).getResultList();
    }

    private FullTextEntityManager getFullTextEntityMananger() {
        return Search.getFullTextEntityManager(entityManager);
    }

    private javax.persistence.Query createFullTextQuery(FullTextEntityManager fullTextEntityManager, org.apache.lucene.search.Query query) {
        return fullTextEntityManager.createFullTextQuery(query, Employee.class);

    }
}
