package es.urjccode.mastercloudapps.adcs.draughts.views;

import java.util.regex.Pattern;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

class PlayView extends SubView {

    private static final String[] COLORS = { "blancas", "negras" };
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String CANCEL_FORMAT = "-1";
    private static final String MOVEMENT_FORMAT = "[1-8]{2}(.[1-8]{2}){1,2}";

    Coordinate first;
    Coordinate second;
    Coordinate third;

    PlayView() {
        super();
        this.first = null;
        this.second = null;
        this.third = null;
    }

    void interact(PlayController playController) {
        assert playController != null;
        Error error;
        do {
            error = null;
            String format = this.readFormat(playController.getColor());
            if (this.isCanceled(format)) {
                playController.cancel();
            } else {
                error = this.isCorrectMovement(format);
                if (error != null) {
                    this.console.write(PlayView.ERROR_MESSAGE);
                } else {
                    error = playController.move(this.first, this.second, this.third);
                    if (error == null) {
                        if (playController.isBlocked()) {
                            this.console.writeln(PlayView.LOST_MESSAGE);
                        }
                    }
                }
            }
        } while (error != null);
    }

    private String readFormat(Color color) {
        final String titleColor = PlayView.COLORS[color.ordinal()];
        return this.console.readString("Mueven las " + titleColor + ": ");
    }

    private boolean isCanceled(String string){
        return string.equals(PlayView.CANCEL_FORMAT);
    }

    private Error isCorrectMovement(String string) {
        if (!this.isCorrectFormat(string))
            return Error.BAD_FORMAT;
        this.first = Coordinate.getInstance(string.substring(0, 2));
        this.second = Coordinate.getInstance(string.substring(3, 5));
        return null;
    }

    boolean isCorrectFormat(String string) {
        return Pattern.compile(PlayView.MOVEMENT_FORMAT).matcher(string).find();
    }

}