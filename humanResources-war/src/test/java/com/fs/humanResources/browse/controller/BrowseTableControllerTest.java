package com.fs.humanResources.browse.controller;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.browse.model.BrowseTableModel;
import com.fs.humanResources.browse.view.BrowseViewBean;
import com.fs.humanResources.employee.controller.EmployeeController;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

public class BrowseTableControllerTest extends BaseUnitTest {

    @InjectMocks
    BrowseTableController browseTableController;

    @Mock
    BrowseTableModel browseTableModel;

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
        BrowseViewBean browseViewBean = new BrowseViewBean(employeeViewBean);

        browseTableController.setSelectedEmployee(browseViewBean);
        Assert.assertEquals(browseViewBean,browseTableController.getSelectedEmployee());
    }

    @Test
    public void getLazyModel_callExpectedMethod() {
        browseTableController.getLazyModel();
        verify(browseTableModel, times(1)).getDataModel();
    }

    @Test
    public void clearDataModel_callExpectedMethod() {
        browseTableController.clearDataModel();
        verify(browseTableModel, times(1)).clearDataModel();
    }

    @Test
    public void loadEmployee_callExpectedMethod() {
        EmployeeViewBean employeeViewBean = new EmployeeViewBean();
        BrowseViewBean browseViewBean = new BrowseViewBean(employeeViewBean);

        browseTableController.setSelectedEmployee(browseViewBean);

        browseTableController.loadEmployee();
        verify(employeeController, times(1)).getEmployeeModel();
        verify(employeeModel, times(1)).setEmployee(eq(employeeViewBean));
    }
}
