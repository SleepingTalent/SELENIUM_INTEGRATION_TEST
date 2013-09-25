package com.fs.humanResources.search.strategy;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.VarargMatcher;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeSearchStrategyTest extends BaseUnitTest {

    Logger log = Logger.getLogger(EmployeeSearchStrategyTest.class);


    @InjectMocks
    EmployeeSearchStrategy employeeSearchStrategy;

    @Mock
    EntityManager entityManager;


    @Mock
    FullTextEntityManager fullTextEntityManager;

    @Mock
    SearchFactory searchFactory;

    @Mock
    QueryContextBuilder queryContextBuilder;

    @Mock
    EntityContext entityContext;

    @Mock
    QueryBuilder queryBuilder;

    @Mock
    TermContext termContext;

    @Mock
    TermMatchingContext termMatchingContext;

    @Mock
    TermTermination termTermination;

    @Mock
    Query query;

    @Mock
    FullTextQuery fullTextQuery;

    List<Employee> resultList;

    private static String[] SEARCHABLE_FIELDS = {
            "id", "firstName", "lastName","addressList.houseNumber","addressList.postCode"};

    @Before
    public void setUp() {
        resultList = new ArrayList<Employee>();
        Employee employee = new Employee();
        employee.setId(1234l);
        resultList.add(employee);

        when(fullTextEntityManager.getSearchFactory()).thenReturn(searchFactory);
        when(searchFactory.buildQueryBuilder()).thenReturn(queryContextBuilder);
        when(queryContextBuilder.forEntity(Employee.class)).thenReturn(entityContext);
        when(entityContext.get()).thenReturn(queryBuilder);
        when(queryBuilder.keyword()).thenReturn(termContext);
        when(termContext.onFields((String)anyVararg())).thenReturn(termMatchingContext);
        when(termMatchingContext.matching(eq("searchTerm"))).thenReturn(termTermination);
        when(termTermination.createQuery()).thenReturn(query);

        when(fullTextEntityManager.createFullTextQuery(eq(query), eq(Employee.class))).thenReturn(fullTextQuery);
        when(fullTextQuery.getResultList()).thenReturn(resultList);
    }

    @Test
    public void executeSeatch_callsExpectedMethods() {
        List<Employee> actualResults = employeeSearchStrategy.executeSearch("searchTerm");

        Assert.assertEquals(1,actualResults.size());
        Assert.assertEquals(new Long(1234),actualResults.get(0).getId());

        verify(fullTextEntityManager, times(1)).getSearchFactory();
        verify(searchFactory, times(1)).buildQueryBuilder();
        verify(queryContextBuilder, times(1)).forEntity(eq(Employee.class));
        verify(entityContext, times(1)).get();

        verify(queryBuilder, times(1)).keyword();
        verify(termContext, times(1)).onFields(argThat(new SearchFieldVarargMatcher(SEARCHABLE_FIELDS)));

        verify(fullTextEntityManager,times(1)).createFullTextQuery(eq(query), eq(Employee.class));
        verify(fullTextQuery,times(1)).getResultList();
    }

    private class SearchFieldVarargMatcher extends ArgumentMatcher<String[]> implements VarargMatcher {

        private String[] expectedValues;

        SearchFieldVarargMatcher(String... expectedValues) {
            this.expectedValues = expectedValues;
        }

        @Override
        public boolean matches(Object varargArgument) {
            log.info("Matching : ("+Arrays.toString((String[])varargArgument)+") with ("+Arrays.toString(expectedValues)+")");
            return new EqualsBuilder().append(expectedValues, varargArgument).isEquals();
        }
    }
}
