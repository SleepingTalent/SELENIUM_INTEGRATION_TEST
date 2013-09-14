package com.fs.humanResources.search.strategy;

import java.util.List;

public interface SearchStrategy<T> {
    List<T> executeSearch(String searchTerm);
}
