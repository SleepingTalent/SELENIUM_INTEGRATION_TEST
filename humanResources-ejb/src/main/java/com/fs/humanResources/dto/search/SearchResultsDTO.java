package com.fs.humanResources.dto.search;

import com.fs.humanResources.dto.employee.EmployeeDTO;

import java.util.List;

public class SearchResultsDTO {

    List<EmployeeDTO> paginatedResults;

    int totalResults;

    public SearchResultsDTO(List<EmployeeDTO> paginatedResults, int totalResults) {
        this.paginatedResults = paginatedResults;
        this.totalResults = totalResults;
    }

    public List<EmployeeDTO> getPaginatedResults() {
        return paginatedResults;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
