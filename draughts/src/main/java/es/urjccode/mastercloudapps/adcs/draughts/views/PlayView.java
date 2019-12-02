package es.urjccode.mastercloudapps.adcs.draughts.views;

import java.util.regex.Pattern;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

class PlayView extends SubView {

    private static final String[] COLORS = { "blancas", "negras" };
    private static final String MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final String FORMAT = "xx.xx";
    private static final String CANCEL = "-1";

    Coordinate origin = null;
    Coordinate target = null;

    PlayView() {
        super();
    }

    void interact(PlayController playController) {
        assert playController != null;
        Error error = null;
        do {
            String format = this.readFormat(playController.getColor());
            if (format.equals(PlayView.CANCEL)){
                playController.cancel();
            }
            error = this.isCorrect(format);
            if (error != null) {
                this.console.write("Error!!! Formato incorrecto");
            } else {
                error = playController.move(origin, target);
                if (error == null) {
                    if (playController.isBlocked()) {
                        this.console.writeln(PlayView.MESSAGE);
                    }
                }
            }
        } while (error != null);

    }

    String readFormat(Color color) {
        final String titleColor = PlayView.COLORS[color.ordinal()];
        return this.console.readString("Mueven las " + titleColor + ": ");
    }

    Error isCorrect(String format) {
        if (format.length() != PlayView.FORMAT.length())
            return Error.BAD_FORMAT;
        this.origin = Coordinate.getInstance(format.substring(0, 2));
        this.target = Coordinate.getInstance(format.substring(3, 5));
        if (this.origin == null || this.target == null)
            return Error.BAD_FORMAT;
        return null;
    }

    boolean isMovement(String string) {
        return Pattern.compile("[1-8]{2}(.[1-8]{2}){1,2}").matcher(string).find();
    }

}