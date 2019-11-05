package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Session;

public abstract class Controller {

    protected Session session;

    protected Controller(Session session) {
		this.session = session;
    }

    public Color getColor(Coordinate coordinate) {
		return this.session.getColor(coordinate);
	}

	public int getDimension() {
		return this.session.getDimension();
	}

	abstract public void accept(ControllersVisitor controllersVisitor);
    
}
