package com.fs.humanResources.search.controller;

import com.fs.humanResources.search.model.SearchTableModel;
import com.fs.humanResources.service.HumanResourcesService;
import com.fs.humanResources.view.helper.ViewHelper;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchController {

    Logger log = Logger.getLogger(SearchController.class);

    @Inject
    private SearchTableModel searchTableModel;

    protected ViewHelper viewHelper;

    @PostConstruct
    public void postContruct() {
        viewHelper = new ViewHelper();
    }

    public SearchTableModel getSearchTableModel() {
        return searchTableModel;
    }

    public void clearSearchTerm() {
        getSearchTableModel().setSearchTerm("");
    }
}
