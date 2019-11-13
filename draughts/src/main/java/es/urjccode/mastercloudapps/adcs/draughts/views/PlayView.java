package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

class PlayView extends SubView {

    private static final String[] COLORS = { "blancas", "negras" };
    private static final String MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final String FORMAT = "xx.xx";

    PlayView() {
        super();
    }

    void interact(PlayController playController) {
        assert playController != null;
        Coordinate origin = null;
        Coordinate target = null;
        Error error;
        do {
            error = null;
            String color = PlayView.COLORS[playController.getColor().ordinal()];
            String format = this.console.readString("Mueven las " + color + ": ");
            if (format.length() != PlayView.FORMAT.length()) {
                this.console.write("Error!!! Formato incorrecto");
                error = Error.BAD_FORMAT;
            } else {
                origin = Coordinate.getInstance(format.substring(0, 2));
                target = Coordinate.getInstance(format.substring(3, 5));
                if (origin == null || target == null) {
                    error = Error.BAD_FORMAT;
                } 
            }
        } while (error != null);
        error = playController.isCorrect(origin, target);
        if (error == null){
            playController.move(origin, target);
            if (playController.isBlocked()){
                this.console.writeln(PlayView.MESSAGE);
            }
        }
    }

}