package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import java.util.HashMap;
import java.util.Map;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class Logic {

	private Game game;
	private State state;
    private StartController startController;
    private PlayController playController;
    private ResumeController resumeController;
	private Map<StateValue, Controller> controllers;

	public Logic() {
		this.game = new Game();
		this.state = new State();
        this.controllers = new HashMap<StateValue, Controller>();
		this.startController = new StartController(this.game, this.state);
		this.playController = new PlayController(this.game, this.state);
		this.resumeController = new ResumeController(this.game, this.state);
		this.controllers.put(StateValue.INITIAL, this.startController);
		this.controllers.put(StateValue.IN_GAME, this.playController);
		this.controllers.put(StateValue.FINAL, this.resumeController);
		this.controllers.put(StateValue.EXIT, null);
	}

	public Controller getController() {
		return this.controllers.get(this.state.getValueState());
    }

}
