package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

public class ResumeView extends SubView {

    private static final String MESSAGE = "¿Queréis jugar otra";
    public ResumeView(){
        super();
    }

    public void interact(ResumeController resumeController) {
        if (new YesNoDialog().read(ResumeView.MESSAGE)){
            resumeController.reset();
        } else {
            resumeController.next();
        }

    }
}
