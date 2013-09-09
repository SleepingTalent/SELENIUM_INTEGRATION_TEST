package com.fs.humanResources.view.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesHelper {

    public void failValidation() {
       FacesContext.getCurrentInstance().validationFailed();
    }

    public void addErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,summary,detail));
    }
}
