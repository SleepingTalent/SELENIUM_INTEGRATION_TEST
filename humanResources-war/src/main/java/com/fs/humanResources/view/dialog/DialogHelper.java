package com.fs.humanResources.view.dialog;

import org.primefaces.context.RequestContext;

public class DialogHelper {

    public void hideDialog(Dialogs dialog) {
        RequestContext.getCurrentInstance().execute(dialog.getDialogWidgetVar() + ".hide()");
    }

    public void showDialog(Dialogs dialog) {
        RequestContext.getCurrentInstance().execute(dialog.getDialogWidgetVar() + ".show()");
    }
}
