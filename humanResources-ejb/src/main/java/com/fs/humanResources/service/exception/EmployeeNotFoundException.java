package com.fs.humanResources.service.exception;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(NoResultException e) {
        super(e);
    }
}
