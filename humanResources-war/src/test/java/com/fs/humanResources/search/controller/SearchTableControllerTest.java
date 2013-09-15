package com.fs.humanResources.search.controller;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.browse.view.BrowseViewBean;
import com.fs.humanResources.employee.controller.EmployeeController;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.search.model.SearchTableModel;
import com.fs.humanResources.search.view.SearchViewBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class SearchTableControllerTest extends BaseUnitTest {

    @InjectMocks
    SearchTableController searchTableController;

    @Mock
    SearchTableModel searchTableModel;

    @Mock
    EmployeeController employeeController;

    @Mock
    EmployeeModel employeeModel;

    @Before
    public void setUp() {
        when(employeeController.getEmployeeModel()).thenReturn(employeeModel);
    }

    @Test
    public void selectedEmployee_setsAsExpected() {
        EmployeeViewBean employeeViewBean = new EmployeeViewBean();
        SearchViewBean searchViewBean = new SearchViewBean(employeeViewBean);

        searchTableController.setSelectedEmployee(searchViewBean);
        Assert.assertEquals(searchViewBean, searchTableController.getSelectedEmployee());
    }

    @Test
    public void getLazyModel_callExpectedMethod() {
        searchTableController.getLazyModel();
        verify(searchTableModel, times(1)).getDataModel();
    }

    @Test
    public void clearDataModel_callExpectedMethod() {
        searchTableController.clearDataModel();
        verify(searchTableModel, times(1)).clearDataModel();
    }

    @Test
    public void loadEmployee_callExpectedMethod() {
        EmployeeViewBean employeeViewBean = new EmployeeViewBean();
        SearchViewBean searchViewBean = new SearchViewBean(employeeViewBean);

        searchTableController.setSelectedEmployee(searchViewBean);

        searchTableController.loadEmployee();
        verify(employeeController, times(1)).getEmployeeModel();
        verify(employeeModel, times(1)).setEmployee(eq(employeeViewBean));
    }
}
