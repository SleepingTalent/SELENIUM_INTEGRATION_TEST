package com.fs.humanResources.search.service;

import com.fs.humanResources.dto.common.DtoHelper;
import com.fs.humanResources.dto.search.SearchResultsDTO;
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
import java.util.Collections;
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
    public SearchResultsDTO performSearch(String searchTerm, int first, int pageSize) {
        log.info("Executing Search for : "+searchTerm);
        return executeSearch(searchTerm,first,pageSize);
    }

    private SearchResultsDTO executeSearch(String searchTerm, int first, int pageSize) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityMananger();

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Employee.class).get();

        Query query = queryBuilder.keyword()
                .onFields(SEARCHABLE_FIELDS)
                .matching(searchTerm)
                .createQuery();

        javax.persistence.Query paginatedResultsQuery = createFullTextQuery(fullTextEntityManager, query);
        paginatedResultsQuery.setFirstResult(first);
        paginatedResultsQuery.setMaxResults(pageSize);

        List<Employee> paginatedResults = paginatedResultsQuery.getResultList();
        log.info(paginatedResults.size()+" results returned!");

        Collections.sort(paginatedResults);

        javax.persistence.Query totalResultsQuery = createFullTextQuery(fullTextEntityManager, query);

        int totalResultCount = totalResultsQuery.getResultList().size();

        SearchResultsDTO searchResultsDTO = new SearchResultsDTO(DtoHelper.create().getDTOList(paginatedResults),totalResultCount);

        return searchResultsDTO;
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
