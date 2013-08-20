package com.fs.humanResources.model.common.factory;


import com.fs.humanResources.model.address.dao.AddressDAO;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;

import java.util.HashMap;

public class DAOFactory {

    private static DAOFactory instance;
    private HashMap daoCache;

    public static DAOFactory getInstance() {
        if(instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
    }

    private DAOFactory() {
        daoCache = new HashMap<String,Object>();
    }

    public EmployeeDAO newEmployDAO() {
        if(!daoCache.containsKey(EmployeeDAO.class)){
            daoCache.put(EmployeeDAO.class, new EmployeeDAO());
        }

        return (EmployeeDAO) daoCache.get(EmployeeDAO.class);
    }

    public AddressDAO newAddressDAO() {
        if(!daoCache.containsKey(AddressDAO.class)){
            daoCache.put(AddressDAO.class, new AddressDAO());
        }

        return (AddressDAO) daoCache.get(AddressDAO.class);
    }
}

