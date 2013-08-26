package com.fs.humanResources.navigation.outcome;

public enum NavigationOutcomes {

    ADD_EMPLOYEE("addEmployee"),
    EDIT_EMPLOYEE("editEmployee"),
    DELETE_EMPLOYEE("deleteEmployee"),
    BROWSE_TO_EMPLOYEE("browseToEmployee"),
    SEARCH_FOR_EMPLOYEE("searchForEmployee");

    private String outcome;

    NavigationOutcomes(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }
}
