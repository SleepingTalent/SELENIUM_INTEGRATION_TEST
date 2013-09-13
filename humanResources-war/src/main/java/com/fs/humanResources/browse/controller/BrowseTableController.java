package com.fs.humanResources.browse.controller;

import com.fs.humanResources.browse.model.BrowseTableModel;
import com.fs.humanResources.browse.view.BrowseViewBean;
import com.fs.humanResources.employee.controller.EmployeeController;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BrowseTableController {

    Logger log = Logger.getLogger(BrowseTableController.class);

    @Inject
    BrowseTableModel browseTableModel;

    @Inject
    EmployeeController employeeController;

    BrowseViewBean selectedViewBean;

    public BrowseViewBean getSelectedEmployee() {
        return selectedViewBean;
    }

    public void setSelectedEmployee(BrowseViewBean selectedViewBean) {
        this.selectedViewBean = selectedViewBean;
    }

    public LazyDataModel getLazyModel() {
        return browseTableModel.getDataModel();
    }

    public void clearDataModel() {
        browseTableModel.clearDataModel();
    }

    public void loadEmployee() {
        BrowseViewBean browseViewBean = getSelectedEmployee();
        employeeController.getEmployeeModel().setEmployee(browseViewBean.getEmployee());
    }

    public void onRowSelect(SelectEvent event) {
        BrowseViewBean browseViewBean = (BrowseViewBean) event.getObject();
        log.info("Selected :" + browseViewBean.getEmployee());
        setSelectedEmployee(browseViewBean);
    }

}
