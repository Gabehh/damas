package es.urjccode.mastercloudapps.adcs.draughts.views;

import java.util.ArrayList;
import java.util.List;
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
    private static final String MINIMUM_MOVEMENT_FORMAT = "dd.dd";

    PlayView() {
        super();
    }

    void interact(PlayController playController) {
        assert playController != null;
        Error error;
        do {
            error = null;
            String string = this.readFormat(playController.getColor());
            if (this.isCanceled(string))
                playController.cancel();
            else if (!this.isCorrectMoveFormat(string)) {
                error = Error.BAD_FORMAT;
                this.console.write(PlayView.ERROR_MESSAGE);
            } else {
                error = playController.move(this.getCoordinates(string));
                if (error == null && playController.isBlocked())
                    this.console.writeln(PlayView.LOST_MESSAGE);
            }
        } while (error != null);
    }

    private String readFormat(Color color) {
        final String titleColor = PlayView.COLORS[color.ordinal()];
        return this.console.readString("Mueven las " + titleColor + ": ");
    }

    private boolean isCanceled(String string) {
        return string.equals(PlayView.CANCEL_FORMAT);
    }

    private Coordinate[] getCoordinates(String string) {
        assert string.length() >= PlayView.MINIMUM_MOVEMENT_FORMAT.length();
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        while (string.length() > 0){
            coordinateList.add(Coordinate.getInstance(string.substring(0, 2)));
            string = string.substring(2, string.length());
            if (string.length() > 0 && string.charAt(0) == '.')
                string = string.substring(1, string.length());
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for(int i=0; i< coordinates.length; i++){
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }

    boolean isCorrectMoveFormat(String string) {
        return Pattern.compile(PlayView.MOVEMENT_FORMAT).matcher(string).find();
    }

}