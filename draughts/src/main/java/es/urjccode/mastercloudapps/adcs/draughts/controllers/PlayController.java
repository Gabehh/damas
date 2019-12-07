package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;

public class PlayController extends InteractorController {

	private CancelController cancelController;

	public PlayController(Game game, State state) {
		super(game, state);
		this.cancelController = new CancelController(game, state);
	}

	public Error move(Coordinate... coordinates) {
		assert 2 <= coordinates.length && coordinates.length <= 3;
		for(Coordinate coordinate: coordinates)
			assert coordinate != null;
		Error error = this.game.move(coordinates);
		if (this.game.isBlocked())
			this.state.next();
		return error;
	}

	public void cancel() {
		this.cancelController.cancel();
	}

	public Color getColor() {
		return this.game.getColor();
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	@Override
	public void accept(InteractorControllersVisitor controllersVisitor) {
		assert controllersVisitor != null;
		controllersVisitor.visit(this);
	}

}