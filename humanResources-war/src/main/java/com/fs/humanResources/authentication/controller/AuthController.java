package com.fs.humanResources.authentication.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthController implements Serializable {

    public void login() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public void logout() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
    }
}
