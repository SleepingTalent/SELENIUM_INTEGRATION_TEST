package com.fs.humanResources.authentication.controller;


import com.fs.common.BaseUnitTest;
import com.fs.common.FacesContextMocker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthControllerTest extends BaseUnitTest {

    @InjectMocks
    AuthController authController;


    FacesContext facesContext;

    @Mock
    ExternalContext externalContext;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Before
    public void setUp() {
        facesContext = FacesContextMocker.mockFacesContext();
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(externalContext.getRequest()).thenReturn(request);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    public void login_logsInAsExpected() {
        authController.login();

        verify(facesContext, times(1)).getExternalContext();
        verify(externalContext, times(1)).getSession(eq(true));
    }

    @Test
    public void logout_logsInAsExpected() {
        authController.logout();

        verify(facesContext, times(1)).getExternalContext();
        verify(externalContext, times(1)).getRequest();
        verify(request, times(1)).getSession();
        verify(session, times(1)).invalidate();
    }
}
