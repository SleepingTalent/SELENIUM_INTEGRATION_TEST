package com.fs.humanResources.search.service;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.search.strategy.SearchStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SearchServiceTest extends BaseUnitTest {

    @InjectMocks
    SearchService searchService;

    @Mock
    SearchStrategy searchStrategy;

    @Mock
    EntityManager entityManager;

    @Mock
    EntityTransaction transaction;

    @Before
    public void setUp() {
        when(entityManager.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void init_initialisesSearchStrategy_asExpected() {
        searchService.init();
        Assert.assertEquals("com.fs.humanResources.search.service.SearchService",searchService.getClass().getName());
    }

    @Test
    public void performSearch_callsExpectedMethods() {
        searchService.performSearch("searchTerm");

        verify(entityManager,times(2)).getTransaction();
        verify(entityManager,times(1)).close();

        verify(transaction,times(1)).begin();
        verify(transaction,times(1)).commit();

        verify(searchStrategy,times(1)).executeSearch(eq("searchTerm"));
    }
}
