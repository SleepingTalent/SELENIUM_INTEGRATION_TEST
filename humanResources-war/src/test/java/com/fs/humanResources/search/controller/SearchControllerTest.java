package com.fs.humanResources.search.controller;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.search.model.SearchTableModel;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import com.fs.humanResources.view.helper.ViewHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import javax.ejb.EJBException;
import javax.faces.event.AbortProcessingException;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


public class SearchControllerTest extends BaseUnitTest {

    @InjectMocks
    SearchController searchController;

    @Mock
    SearchTableModel searchTableModel;

    @Mock
    ViewHelper viewHelper;

    @Before
    public void setUp() throws EmployeeNotFoundException {
    }

    @Test
    public void clearEmployeeViewBean_setsModel_asExpected() {
        searchController.clearSearchTerm();
        verify(searchTableModel, times(1)).setSearchTerm(eq(""));
    }

    @Test
    public void postContruct_createsViewHelperInstance() {
        searchController.postContruct();
        Assert.assertEquals("com.fs.humanResources.view.helper.ViewHelper", searchController.viewHelper.getClass().getName());
    }
}