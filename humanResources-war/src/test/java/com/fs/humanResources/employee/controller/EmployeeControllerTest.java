package com.fs.humanResources.employee.controller;

import com.fs.common.BaseUnitTest;
import com.fs.common.ContextMocker;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.address.AddressViewBean;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import java.util.Date;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EmployeeControllerTest extends BaseUnitTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeModel employeeModel;

    @Mock
    HumanResourcesService humanResourcesService;

    EmployeeViewBean employee;

    EmployeeDTO employeeDTO;

    AddressDTO addressDTO;

    FacesContext facesContext;

    ArgumentCaptor<FacesMessage> facesMessageArgumentCaptor = ArgumentCaptor.forClass(FacesMessage.class);
    ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setUp() throws EmployeeNotFoundException {
        facesContext = ContextMocker.mockFacesContext();

        employee = createEmployee();

        addressDTO = new AddressDTO(employee.getAddress().getId(),employee.getAddress().getHouseNumber(),
                employee.getAddress().getAddressFirstLine(),employee.getAddress().getAddressSecondLine(),
                employee.getAddress().getTownCity(),employee.getAddress().getPostCode(),true);

        employeeDTO = new EmployeeDTO(employee.getEmployeeId(),employee.getFirstName()
                ,employee.getLastName(),employee.getDateOfBirth(),addressDTO);

        when(employeeModel.getEmployee()).thenReturn(employee);
        when(humanResourcesService.getEmployeeDetails(eq(employee.getEmployeeId()))).thenReturn(employeeDTO);
    }

    @After
    public void tearDown() {
        facesContext.release();

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

        AddressViewBean address = new AddressViewBean(addressId,houseNumber, addressFirstLine, addressSecondLine, townCity, postCode);
        return new EmployeeViewBean(employeeId,firstName, lastName, dataOfBirth, address);
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
    public void findEmployee_findsEmployee_asExpected() throws EmployeeNotFoundException {
        employeeController.findEmployee();

        verify(employeeModel, times(2)).getEmployee();
        verify(humanResourcesService, times(1)).getEmployeeDetails(eq(employee.getEmployeeId()));
        verify(employeeModel, times(1)).setEmployee(Matchers.<EmployeeViewBean>anyObject());
    }

    @Test
    public void findEmployee_logsExpectedMessage_whenIdNotFound() throws EmployeeNotFoundException {
        when(humanResourcesService.getEmployeeDetails(anyLong())).thenThrow(new EJBException());

        employeeController.findEmployee();

        verify(facesContext, times(1)).addMessage(stringArgumentCaptor.capture(),facesMessageArgumentCaptor.capture());
        verify(facesContext, times(1)).validationFailed();

        Assert.assertEquals(null, stringArgumentCaptor.getValue());
        Assert.assertEquals("Employee Id (12345) not found!", facesMessageArgumentCaptor.getValue().getSummary());
        Assert.assertEquals("", facesMessageArgumentCaptor.getValue().getDetail());
    }
}