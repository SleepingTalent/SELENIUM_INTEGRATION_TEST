package com.fs.humanResources.search.strategy;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
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

public class EmployeeSearchStrategyTest extends BaseUnitTest {

    Logger log = Logger.getLogger(EmployeeSearchStrategyTest.class);

    @InjectMocks
    EmployeeSearchStrategy employeeSearchStrategy;

    @Mock
    EntityManager entityManager;

    List<Employee> resultList;

    private static String[] SEARCHABLE_FIELDS = {
            "id", "firstName", "lastName","addressList.houseNumber","addressList.postCode"};

    @Before
    public void setUp() {
        resultList = new ArrayList<Employee>();
        Employee employee = new Employee();
        employee.setId(1234l);
        resultList.add(employee);
    }

    @Test
    public void executeSeatch_returnsExpectedSize() {
        List<Employee> actualResults = employeeSearchStrategy.executeSearch("searchTerm");

        Assert.assertEquals(0,actualResults.size());
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
