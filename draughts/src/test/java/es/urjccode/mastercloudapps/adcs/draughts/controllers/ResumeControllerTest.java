package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Session;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class ResumeControllerTest {

    @Test
    public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {
        Session session= new Session();
        ResumeController resumeController = new ResumeController(session);
        assertEquals(StateValue.INITIAL, session.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, session.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, session.getValueState());
        resumeController.reset();
        assertEquals(StateValue.INITIAL, session.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void givenResumeControllerWhenResumeGameMoveOutThenError() {
        Session session= new Session();
        ResumeController resumeController = new ResumeController(session);
        assertEquals(StateValue.INITIAL, session.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, session.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, session.getValueState());
        resumeController.next();
        assertEquals(StateValue.EXIT, session.getValueState());
        resumeController.next();
    }
}