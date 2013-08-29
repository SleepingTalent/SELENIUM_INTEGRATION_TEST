function handleDialogSubmit(xhr, status, args, dialog) {
    if (args.validationFailed) {
       showDialog(dialog);
    } else {
       hideDialog(dialog);
    }
}

function handleDialogSubmitAndNextDialog(xhr, status, args, dialog, nextDialog) {
    if (args.validationFailed) {
        showDialog(dialog);
    } else {
        hideDialog(dialog);
        showDialog(nextDialog);
    }
}

function showDialog(dialog) {
    dialog.show();
}

function hideDialog(dialog) {
    dialog.hide();
}