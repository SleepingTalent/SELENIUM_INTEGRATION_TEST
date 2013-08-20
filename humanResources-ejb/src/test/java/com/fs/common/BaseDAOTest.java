package com.fs.common;

import com.fs.helper.EntityManagerHelper;
import com.fs.helper.PersitenceHelper;
import com.fs.humanResources.model.address.helper.AddressHelper;

import javax.persistence.EntityManager;

public class BaseDAOTest {

    private static EntityManager entityManager = EntityManagerHelper.getEntityManagerFactory().createEntityManager();

    protected PersitenceHelper persitenceHelper;

    public BaseDAOTest()  {
         persitenceHelper = new PersitenceHelper(entityManager);
    }

    protected void beginTransaction() {
        persitenceHelper.beginTransaction();
    }

    protected void endTransaction()  {
        persitenceHelper.rollbackTransaction();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected AddressHelper getAddressHelper() {
        return new AddressHelper();
    }
}
