package com.fs.humanResources.search.controller;

import com.fs.humanResources.employee.controller.EmployeeController;
import com.fs.humanResources.search.model.SearchTableModel;
import com.fs.humanResources.search.view.SearchViewBean;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchTableController {

    Logger log = Logger.getLogger(SearchTableController.class);

    @Inject
    SearchTableModel searchTableModel;

    @Inject
    EmployeeController employeeController;

    SearchViewBean selectedViewBean;

    public SearchViewBean getSelectedEmployee() {
        return selectedViewBean;
    }

    public void setSelectedEmployee(SearchViewBean selectedViewBean) {
        this.selectedViewBean = selectedViewBean;
    }

    public LazyDataModel getLazyModel() {
        return searchTableModel.getDataModel();
    }

    public void clearDataModel() {
        searchTableModel.clearDataModel();
    }

    public void loadEmployee() {
        SearchViewBean browseViewBean = getSelectedEmployee();
        employeeController.getEmployeeModel().setEmployee(browseViewBean.getEmployee());
    }
}
