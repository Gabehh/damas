package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;

public class StartView extends SubView {

    private static final String TITTLE = "Draughts";

    public StartView(){
        super();
    }

    public void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(StartView.TITTLE);
        new GameView().write(startController);
        startController.start();
    }
}
