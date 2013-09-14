package com.fs.common;

import com.fs.helper.EntityManagerHelper;
import com.fs.helper.PersitenceHelper;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;

public class BaseSearchTest {

    protected static final String DATE_FORMATE_STR = "dd/MM/yyyy";

    private static EntityManager entityManager = EntityManagerHelper.getEntityManagerFactory().createEntityManager();

    protected PersitenceHelper persitenceHelper;

    protected SimpleDateFormat simpleDateFormat;

    public BaseSearchTest()  {
        persitenceHelper = new PersitenceHelper(entityManager);
        simpleDateFormat = new SimpleDateFormat(DATE_FORMATE_STR);
    }

    protected void beginTransaction() {
        persitenceHelper.beginTransaction();
    }

    protected void commitTransaction()  {
        persitenceHelper.commitTransaction();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
