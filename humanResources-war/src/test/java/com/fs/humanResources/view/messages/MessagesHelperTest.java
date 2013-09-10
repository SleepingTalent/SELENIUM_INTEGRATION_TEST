package com.fs.humanResources.view.messages;

import com.fs.common.BaseUnitTest;
import com.fs.common.FacesContextMocker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessagesHelperTest extends BaseUnitTest {

    @InjectMocks
    MessagesHelper messagesHelper;

    FacesContext facesContext;

    @Before
    public void setUp() {
        facesContext = FacesContextMocker.mockFacesContext();
    }

    @After
    public void tearDown() {
        facesContext.release();
    }

    @Test
    public void addErrorMessage_callsExpectedMethods(){
        ArgumentCaptor<FacesMessage> facesMessageArgumentCaptor = ArgumentCaptor.forClass(FacesMessage.class);
        messagesHelper.addErrorMessage("mySummary", "myDetail");

        verify(facesContext, times(1)).addMessage(anyString(), facesMessageArgumentCaptor.capture());

        Assert.assertEquals("mySummary", facesMessageArgumentCaptor.getValue().getSummary());
        Assert.assertEquals("myDetail", facesMessageArgumentCaptor.getValue().getDetail());
        Assert.assertEquals(FacesMessage.SEVERITY_ERROR,facesMessageArgumentCaptor.getValue().getSeverity());
    }
}
