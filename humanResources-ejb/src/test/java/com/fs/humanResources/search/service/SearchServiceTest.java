package com.fs.humanResources.search.service;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.search.strategy.SearchStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class SearchServiceTest extends BaseUnitTest {

    @InjectMocks
    SearchService searchService;

    @Mock
    SearchStrategy searchStrategy;

    @Mock
    EntityManager entityManager;

    @Before
    public void setUp() {
    }

    @Test
    public void init_initialisesSearchStrategy_asExpected() {
        searchService.init();
        Assert.assertEquals("com.fs.humanResources.search.service.SearchService",searchService.getClass().getName());
    }

    @Test
    public void performSearch_callsExpectedMethods() {
        searchService.performSearch("searchTerm");

        verify(searchStrategy,times(1)).executeSearch(eq("searchTerm"));
    }
}
