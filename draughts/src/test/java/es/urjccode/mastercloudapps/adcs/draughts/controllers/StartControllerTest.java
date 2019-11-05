package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Session;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class StartControllerTest {

     @Test
    public void givenStartControllerWhenStartGameThenChangeState() {
        Session session = new Session();
        StartController startController = new StartController(session);
        assertEquals(StateValue.INITIAL, session.getValueState());
        startController.start();
        assertEquals(StateValue.IN_GAME, session.getValueState());
    }

}