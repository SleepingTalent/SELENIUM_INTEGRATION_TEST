package com.fs.humanResources.model.employee.dao;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeDAOTest extends BaseUnitTest {

    private static final String COUNT_QUERY = "SELECT count(o) from Employee o ";

    private static final String FIND_EMPLOYEE_BY_LASTNAME = "select e from Employee e where e.lastName = :thisLastName";

    @InjectMocks
    EmployeeDAO employeeDAO;

    @Mock
    EntityManager entityManager;

    @Mock
    Query countQuery;

    @Mock
    Query employeeIdQuery;

    @Mock
    Query findByLastnameQuery;

    Long expectedCount;

    Employee employee;

    List<Employee> employeeList;


    @Before
    public void setUp() {
        expectedCount = new Long(10l);
        employee = new Employee();
        employee.setId(1234l);

        employeeList = new ArrayList<Employee>();
        employeeList.add(employee);

        when(entityManager.createQuery(eq(COUNT_QUERY))).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(expectedCount);

        when(entityManager.createQuery(eq(FIND_EMPLOYEE_BY_LASTNAME))).thenReturn(findByLastnameQuery);
        when(findByLastnameQuery.getResultList()).thenReturn(employeeList);


        when(entityManager.find(eq(Employee.class),eq(employee.getId()))).thenReturn(employee);
    }

    @Test
    public void countAll_uses_expected_query() {
        Long actualCount = employeeDAO.countAll();

        Assert.assertEquals(expectedCount, actualCount);

        verify(entityManager, times(1)).createQuery(eq(COUNT_QUERY));
        verify(countQuery,times(1)).getSingleResult();
    }

    @Test
    public void create_persits_expected_employee() {
        Employee employee = new Employee();
        employeeDAO.create(employee);

        verify(entityManager, times(1)).persist(eq(employee));
    }

    @Test
    public void delete_removes_expected_employee() {
        Employee employee = new Employee();

        employeeDAO.delete(employee);

        verify(entityManager, times(1)).merge(eq(employee));
        verify(entityManager, times(1)).remove(Matchers.<Employee>anyObject());
    }

    @Test
    public void delete_merges_expected_employee() {
        Employee employee = new Employee();

        employeeDAO.update(employee);

        verify(entityManager, times(1)).merge(eq(employee));
    }

    @Test
    public void findById_looksfor_expected_employee() {
        Long employeeId = 1234l;

        when(entityManager.find(any(Class.class),eq(employeeId))).thenReturn(new Employee());

        employeeDAO.findById(employeeId);

        verify(entityManager, times(1)).find(any(Class.class),eq(employeeId));
    }

    @Test(expected = NoResultException.class)
    public void findById_throws_noResultsException_whenEmployeeNotFound() {
        Long employeeId = 9999l;
        employeeDAO.findById(employeeId);
    }

    @Test
    public void getEmployeeDetails_returns_expected_employee() {
        Long staffNumber = 1234l;

        Employee actualEmployee = employeeDAO.getEmployeeDetails(staffNumber);
        Assert.assertEquals(employee.getId(), actualEmployee.getId());

        verify(entityManager, times(1)).find(eq(Employee.class),eq(employee.getId()));
    }

    @Test(expected = NoResultException.class)
    public void getEmployeesByLastname_throws_noResultsException_whenNullReturned() {
        when(findByLastnameQuery.getResultList()).thenReturn(null);
        employeeDAO.getEmployeesByLastname("notFound");
    }

    @Test(expected = NoResultException.class)
    public void getEmployeesByLastname_throws_noResultsException_whenEmptyListReturned() {
        when(findByLastnameQuery.getResultList()).thenReturn(new ArrayList<Employee>());
        employeeDAO.getEmployeesByLastname("notFound");
    }

    @Test
    public void getEmployeesByLastname_returns_expected_employee() {
        List<Employee> actualEmployeeList = employeeDAO.getEmployeesByLastname("lastName");

        Assert.assertEquals(1, actualEmployeeList.size());
        Assert.assertEquals(employee.getId(), actualEmployeeList.get(0).getId());

        verify(entityManager, times(1)).createQuery(eq(FIND_EMPLOYEE_BY_LASTNAME));
        verify(findByLastnameQuery,times(1)).getResultList();
    }

}
