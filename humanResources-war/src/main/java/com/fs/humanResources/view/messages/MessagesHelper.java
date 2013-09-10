package com.fs.humanResources.view.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesHelper {

    public void addErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

}
