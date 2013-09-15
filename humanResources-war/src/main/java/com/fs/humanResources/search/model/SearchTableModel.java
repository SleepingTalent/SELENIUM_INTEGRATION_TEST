package com.fs.humanResources.search.model;

import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.search.view.SearchViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateful
@SessionScoped
public class SearchTableModel implements Serializable {

    Logger log = Logger.getLogger(SearchTableModel.class);

    @Inject
    private HumanResourcesService humanResourcesService;

    private List<SearchViewBean> lazyLoadedData;

    private LazyDataModel<SearchViewBean> dataModel;

    @PostConstruct
    public void init() {
        lazyLoadedData = new ArrayList<SearchViewBean>();
        dataModel = buildDataModel();
    }

    public void clearDataModel() {
       init();
    }

    public LazyDataModel<SearchViewBean> getDataModel(){
        return dataModel;
    }

    private LazyDataModel<SearchViewBean> buildDataModel() {
        return new LazyDataModel<SearchViewBean>() {

            @Override
            public SearchViewBean getRowData(String rowKey) {
                for(SearchViewBean searchViewBean : lazyLoadedData) {
                    String id = searchViewBean.getEmployee().getEmployeeId().toString();

                    if(id.equals(rowKey)) {
                        return searchViewBean;
                    }
                }

                return null;
            }

            @Override
            public Object getRowKey(SearchViewBean searchViewBean) {
                return searchViewBean.getEmployee().getEmployeeId();
            }

            @Override
            public List<SearchViewBean> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                lazyLoadedData.clear();

                List<EmployeeDTO> employeeDTOList = humanResourcesService.findEmployees(first, pageSize);

                for(EmployeeDTO employeeDTO : employeeDTOList) {
                    log.info("adding "+employeeDTO);
                    EmployeeViewBean employeeViewBean = new EmployeeViewBean(employeeDTO);
                    SearchViewBean searchViewBean = new SearchViewBean(employeeViewBean);
                    lazyLoadedData.add(searchViewBean);
                }

                setRowCount(humanResourcesService.findTotalEmployeeCount());

                return lazyLoadedData;
            }
        };
    }
}
