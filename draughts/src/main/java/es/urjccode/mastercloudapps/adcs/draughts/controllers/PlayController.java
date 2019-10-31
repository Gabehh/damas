package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayController{

	private Game game;

    public PlayController(Game game) {
		this.game = game;
	}

	public Error move(Coordinate origin, Coordinate target){
        return game.move(origin, target);
    }

	public Piece getPiece(Coordinate origin) {
		return null;
	}

	public Color getColor() {
		return null;
	}
}