package com.fs.humanResources.navigation.outcome;

public enum NavigationOutcomes {

    BROWSE_TO_EMPLOYEE("browseToEmployee"),
    SEARCH_FOR_EMPLOYEE("searchForEmployee"),
    ADMIN("admin");

    private String outcome;

    NavigationOutcomes(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }
}
