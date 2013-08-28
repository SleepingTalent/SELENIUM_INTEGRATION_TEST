function handleDialogSubmit(xhr, status, args) {
    if (args.validationFailed) {
        dialog.show();
    } else {
        dialog.hide();
    }
}