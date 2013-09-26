package com.fs.humanResources.model.employee.entities;

import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest extends BaseUnitTest {

    @Test
    public void compareTo_returnsZero_whenIdsAreEqual() {
        Employee employeeOne = new Employee();
        employeeOne.setId(new Long(1));

        Employee employeeTwo = new Employee();
        employeeTwo.setId(new Long(1));

        Assert.assertEquals(0, employeeOne.compareTo(employeeTwo));
    }

    @Test
    public void compareTo_returnsMinus_whenIdIsLessThanComparedEmployee() {
        Employee employeeOne = new Employee();
        employeeOne.setId(new Long(1));

        Employee employeeTwo = new Employee();
        employeeTwo.setId(new Long(2));

        Assert.assertEquals(-1, employeeOne.compareTo(employeeTwo));
    }

    @Test
    public void compareTo_returnsOne_whenIdIsGreaterThanComparedEmployee() {
        Employee employeeOne = new Employee();
        employeeOne.setId(new Long(2));

        Employee employeeTwo = new Employee();
        employeeTwo.setId(new Long(1));

        Assert.assertEquals(1, employeeOne.compareTo(employeeTwo));
    }
}
