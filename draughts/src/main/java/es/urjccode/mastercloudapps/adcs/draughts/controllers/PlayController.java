package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;

public class PlayController extends Controller {

	public PlayController(Game game, State state) {
        super(game, state);
	}

	public Error move(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		Error error = this.game.move(origin, target);
		if (this.game.isBlocked()){
			this.state.next();
		}
		return error;
    }

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.game.getPiece(coordinate);
	}

	public Color getColor() {
		return this.game.getColor();
	}
	
	public boolean isBlocked() {
		return this.game.isBlocked();
	}	

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		assert controllersVisitor != null;
		controllersVisitor.visit(this);
	}

}