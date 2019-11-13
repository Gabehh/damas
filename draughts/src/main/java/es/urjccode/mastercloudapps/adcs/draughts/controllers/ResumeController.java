package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Session;

public class ResumeController extends Controller {

	public ResumeController(Session session) {
        super(session);
    }
    
    @Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

	public void next() {
        this.session.state.next();
	}

	public void reset() {
        this.session.state.reset();
	}

}
