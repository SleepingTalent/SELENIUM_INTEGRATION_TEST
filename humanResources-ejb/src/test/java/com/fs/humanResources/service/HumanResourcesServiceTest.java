package com.fs.humanResources.service;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.address.helper.AddressHelper;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;
import com.fs.humanResources.model.employee.entities.Employee;
import com.fs.humanResources.search.service.SearchService;
import com.fs.humanResources.service.exception.EmployeeNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class HumanResourcesServiceTest extends BaseUnitTest {

    @InjectMocks
    HumanResourcesService humanResourcesService;

    @Mock
    EmployeeDAO employeeDAO;

    Employee employee;

    Long foundEmployeeId;

    Long notFoundEmployeeId;

    @Mock
    EmployeeDTO employeeDTO;

    @Mock
    SearchService searchService;

    List<Employee> employeeList;

    int first = 0;
    int pageSize = 10;

    @Before
    public void setUp() throws NoResultException {
        foundEmployeeId = 12345l;
        notFoundEmployeeId = 4567l;

        employee = createEmployee();

        employeeList = new ArrayList<Employee>();
        employeeList.add(employee);

        when(employeeDTO.getEntity()).thenReturn(employee);

        when(employeeDAO.getEmployeeDetails(eq(foundEmployeeId))).thenReturn(employee);
        when(employeeDAO.getEmployeeDetails(eq(notFoundEmployeeId))).thenThrow(new NoResultException());

        when(employeeDAO.findAll(anyInt(),anyInt())).thenReturn(employeeList);
        when(searchService.performSearch(anyString(), anyInt(), anyInt())).thenReturn(employeeList);
    }

    @Test
    public void getEmployeeDetails_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.getEmployeeDetails(foundEmployeeId);
        verify(employeeDAO,times(1)).getEmployeeDetails(anyLong());
    }

    @Test
    public void getEmployeeDetails_returns_expected_employee() throws NoResultException, EmployeeNotFoundException {
        EmployeeDTO actual = humanResourcesService.getEmployeeDetails(foundEmployeeId);

        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());

        Address expectedAddress = employee.getAddressList().get(0);
        AddressDTO actualAddress = actual.getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeDetails_throws_exception_when_employee_not_found() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.getEmployeeDetails(notFoundEmployeeId);
    }

    @Test
    public void findEmployees_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.findEmployees(anyInt(), anyInt());
        verify(employeeDAO,times(1)).findAll(anyInt(), anyInt());
    }

    @Test
    public void searchForEmployees_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.searchForEmployees("searchTerm", first, pageSize);
        verify(searchService,times(1)).performSearch(eq("searchTerm"), anyInt(), anyInt());
    }


    @Test
    public void findTotalEmployeeCount_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.findTotalEmployeeCount();
        verify(employeeDAO,times(1)).countAll();
    }

    @Test
    public void findEmployees_returns_expected_employee() throws NoResultException, EmployeeNotFoundException {
        List<EmployeeDTO> actual = humanResourcesService.findEmployees(0,0);

        Assert.assertEquals(1,actual.size());
        Assert.assertEquals(employee.getFirstName(), actual.get(0).getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.get(0).getLastName());
        Assert.assertEquals(employee.getId(), actual.get(0).getId());
        Assert.assertEquals(employee.getDateOfBirth(), actual.get(0).getDateOfBirth());

        Address expectedAddress = employee.getAddressList().get(0);
        AddressDTO actualAddress = actual.get(0).getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }

    @Test
    public void searchForEmployees_returns_expected_employee() throws NoResultException, EmployeeNotFoundException {
        List<EmployeeDTO> actual = humanResourcesService.searchForEmployees("searchTerm", first, pageSize);

        Assert.assertEquals(1,actual.size());
        Assert.assertEquals(employee.getFirstName(), actual.get(0).getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.get(0).getLastName());
        Assert.assertEquals(employee.getId(), actual.get(0).getId());
        Assert.assertEquals(employee.getDateOfBirth(), actual.get(0).getDateOfBirth());

        Address expectedAddress = employee.getAddressList().get(0);
        AddressDTO actualAddress = actual.get(0).getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }

    @Test
    public void saveEmployeeDetails_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.saveEmployeeDetails(employeeDTO);
        verify(employeeDAO,times(1)).create(eq(employee));
    }

    @Test
    public void updateEmployeeDetails_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.updateEmployeeDetails(employeeDTO);
        verify(employeeDAO,times(1)).update(eq(employee));
    }

    @Test
    public void deleteEmployeeDetails_verify_expected_methods_called() throws NoResultException, EmployeeNotFoundException {
        humanResourcesService.deleteEmployeeDetails(employeeDTO);
        verify(employeeDAO,times(1)).delete(eq(employee));
    }

    private Employee createEmployee() {
        String firstName = "Joe";
        String lastName = "Smith";
        Date dataOfBirth = new Date();
        Long employeeId = 12345l;

        String houseNumber = "50";;
        String addressFirstLine = "Test Driven Way";
        String addressSecondLine = "Domain Court";
        String townCity = "Progammer City";
        String postCode = "AB1 CDXY";

        Address address = new Address();
        address.setHouseNumber(houseNumber);
        address.setAddressFirstLine(addressFirstLine);
        address.setAddressSecondLine(addressSecondLine);
        address.setTownCity(townCity);
        address.setPostCode(postCode);
        address.setPrimaryAddress(true);

        Employee employee = new Employee();
        employee.setId(12345l);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dataOfBirth);

        AddressHelper addressHelper = new AddressHelper();
        employee.setAddressList(addressHelper.convertToList(address));

        address.setEmployee(employee);

        return employee;
    }

}
