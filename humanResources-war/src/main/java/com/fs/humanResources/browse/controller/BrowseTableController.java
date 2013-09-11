package com.fs.humanResources.browse.controller;

import com.fs.humanResources.browse.model.BrowseTableModel;
import org.primefaces.model.LazyDataModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BrowseTableController {

    @Inject
    BrowseTableModel browseTableModel;

    public LazyDataModel getTableModel() {
        return browseTableModel.getDataModel();
    }
}
