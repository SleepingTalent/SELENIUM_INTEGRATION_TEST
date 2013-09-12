package com.fs.humanResources.employee.controller;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.address.AddressViewBean;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
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
import java.util.Date;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


public class EmployeeControllerTest extends BaseUnitTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeModel employeeModel;

    @Mock
    HumanResourcesService humanResourcesService;

    @Mock
    ViewHelper viewHelper;

    EmployeeViewBean employee;

    EmployeeDTO employeeDTO;

    AddressDTO addressDTO;

    ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setUp() throws EmployeeNotFoundException {
        employee = createEmployee();

        addressDTO = new AddressDTO(employee.getAddress().getId(), employee.getAddress().getHouseNumber(),
                employee.getAddress().getAddressFirstLine(), employee.getAddress().getAddressSecondLine(),
                employee.getAddress().getTownCity(), employee.getAddress().getPostCode(), true);

        employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getFirstName()
                , employee.getLastName(), employee.getDateOfBirth(), addressDTO);

        when(employeeModel.getEmployee()).thenReturn(employee);
        when(humanResourcesService.getEmployeeDetails(eq(employee.getEmployeeId()))).thenReturn(employeeDTO);
    }

    private EmployeeViewBean createEmployee() {
        String firstName = "Joe";
        String lastName = "Smith";
        Date dataOfBirth = new Date();
        Long employeeId = 12345l;

        Long addressId = 5678l;
        String houseNumber = "50";
        String addressFirstLine = "Test Driven Way";
        String addressSecondLine = "Domain Court";
        String townCity = "Progammer City";
        String postCode = "AB1 CDXY";

        AddressViewBean address = new AddressViewBean(addressId, houseNumber, addressFirstLine, addressSecondLine, townCity, postCode);
        return new EmployeeViewBean(employeeId, firstName, lastName, dataOfBirth, address);
    }


    @Test
    public void getEmployee_returnsAsExpected() {
        EmployeeViewBean actual = employeeController.getEmployee();

        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());

        AddressViewBean expectedAddress = employee.getAddress();
        AddressViewBean actualAddress = actual.getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }

    @Test
    public void clearEmployeeViewBean_setsModel_asExpected() {
        employeeController.clearEmployee();
        verify(employeeModel, times(1)).setEmployee(Matchers.<EmployeeViewBean>anyObject());
    }

    @Test
    public void saveEmployee_savesEmployee_asExpected() {
        employeeController.saveEmployee();
        verify(employeeModel, times(1)).getEmployee();
        verify(humanResourcesService, times(1)).saveEmployeeDetails(Matchers.<EmployeeDTO>anyObject());
    }

    @Test
    public void updateEmployee_savesEmployee_asExpected() {
        employeeController.updateEmployee();
        verify(employeeModel, times(1)).getEmployee();
        verify(humanResourcesService, times(1)).updateEmployeeDetails(Matchers.<EmployeeDTO>anyObject());
    }

    @Test
    public void findEmployee_findsEmployee_asExpected() {
        employeeController.findEmployee();

        verify(employeeModel, times(2)).getEmployee();
        verify(humanResourcesService, times(1)).getEmployeeDetails(eq(employee.getEmployeeId()));
        verify(employeeModel, times(1)).setEmployee(Matchers.<EmployeeViewBean>anyObject());
    }

    @Test
    public void findEmployee_logsExpectedMessage_whenIdNotFound() {
        when(humanResourcesService.getEmployeeDetails(anyLong())).thenThrow(new EJBException());
        try {
            employeeController.findEmployee();
        } catch (AbortProcessingException ape) {

        }

        verify(viewHelper, times(1)).addErrorMessage(stringArgumentCaptor.capture(), eq(""));
        verify(viewHelper, times(1)).failValidation();

        Assert.assertEquals("Employee Id (12345) not found!", stringArgumentCaptor.getValue());
    }

    @Test(expected = AbortProcessingException.class)
    public void findEmployee_throwsAbortProcessingException_whenIdNotFound() {
        when(humanResourcesService.getEmployeeDetails(anyLong())).thenThrow(new EJBException());
        employeeController.findEmployee();
    }

    @Test
    public void saveEmployee_logsExpectedMessage_whenSaveFails() {
        doThrow(new EJBException()).when(
                humanResourcesService).saveEmployeeDetails(Matchers.<EmployeeDTO>anyObject());

        try {
        employeeController.saveEmployee();
        } catch (AbortProcessingException abe) {

        }

        verify(viewHelper, times(1)).addErrorMessage(stringArgumentCaptor.capture(), eq(""));
        verify(viewHelper, times(1)).failValidation();

        Assert.assertEquals("Error Saving : Joe Smith", stringArgumentCaptor.getValue());
    }

    @Test(expected = AbortProcessingException.class)
    public void saveEmployee_throwsAbortProcessingException_whenSaveFails() {
        doThrow(new EJBException()).when(
                humanResourcesService).saveEmployeeDetails(Matchers.<EmployeeDTO>anyObject());

        employeeController.saveEmployee();
    }

    @Test
    public void updateEmployee_logsExpectedMessage_whenSaveFails() {
        doThrow(new EJBException()).when(
                humanResourcesService).updateEmployeeDetails(Matchers.<EmployeeDTO>anyObject());
        try {
        employeeController.updateEmployee();
        } catch (AbortProcessingException abe) {}

        verify(viewHelper, times(1)).addErrorMessage(stringArgumentCaptor.capture(), eq(""));
        verify(viewHelper, times(1)).failValidation();

        Assert.assertEquals("Error Updating : Joe Smith", stringArgumentCaptor.getValue());
    }

    @Test(expected = AbortProcessingException.class)
    public void updateEmployee_throwsAbortProcessingException_whenSaveFails() {
        doThrow(new EJBException()).when(
                humanResourcesService).updateEmployeeDetails(Matchers.<EmployeeDTO>anyObject());

        employeeController.updateEmployee();
    }

    @Test
    public void postContruct_createsViewHelperInstance() {
        employeeController.postContruct();
        Assert.assertEquals("com.fs.humanResources.view.helper.ViewHelper", employeeController.viewHelper.getClass().getName());
    }
}