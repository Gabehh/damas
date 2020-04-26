package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.GameBuilder;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class PlayControllerTest {

    private PlayController playController;

    @Test
    public void testGivenPlayControllerWhenMoveThenOk() {
        Game game = new GameBuilder().build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertFalse(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenMoveWithoutPiecesThenIsBlocked() {
        Game game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            " n      ",
            "b       ",
            "        ",
            "        ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(3, 2);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenMoveWithoutMovementsThenIsBlocked() {
        Game game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "  b b   ",
            "     b  ",
            "b       ",
            "        ",
            "        ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        playController.move(origin, target);
        assertEquals(playController.getColor(target), Color.WHITE);
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenCancelThenOk() {
        Game game = new GameBuilder().build();
        playController = new PlayController(game, new State());
        playController.cancel();
        assertEquals(Color.BLACK, playController.getColor());
        assertFalse(game.isBlocked());
    }

    @Test
    public void testGivenPlayControllerWhenDraughtsCanEatAndWillNotEatBlack() {
        Game game = new GameBuilder().rows(
            "     n n",
            "  B n n ",
            " n      ",
            "      n ",
            "   b    ",
            "    b   ",
            " b n   b",
            "b b   b ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 4);
        Coordinate target = new Coordinate(4, 5);
        playController.move(origin, target);
        assertNull(playController.getPiece(new Coordinate(1, 2)));
    }

    @Test
    public void testGivenPlayControllerWhenDraughtsCanEatAndWillNotEatWhite() {
        Game game = new GameBuilder().rows(
            "     n n",
            "  B n n ",
            " n      ",
            "      n ",
            "   b    ",
            "    b   ",
            " b N   b",
            "b b   b ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(1, 2);
        Coordinate target = new Coordinate(3, 0);
        playController.move(origin, target);
        origin = new Coordinate(3, 6);
        target = new Coordinate(4, 7);
        playController.move(origin, target);
        assertNull(playController.getPiece(new Coordinate(6, 3)));
    }

    @Test
    public void testGivenPlayControllerWhenPieceCanEatAndWillNotEatBlack() {
        Game game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "  b     ",
            "     n  ",
            "b       ",
            "        ",
            "  b     ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        playController.move(origin, target);
        assertNull(playController.getPiece(new Coordinate(3, 2)));
    }

    @Test
    public void testGivenPlayControllerWhenPieceCanEatAndWillNotEatWhite() {
        Game game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "        ",
            " b    n ",
            "b       ",
            "        ",
            "  b     ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(4, 1);
        Coordinate target = new Coordinate(3, 2);
        playController.move(origin, target);
        origin = new Coordinate(4, 6);
        target = new Coordinate(5, 5);
        playController.move(origin, target);
        assertNull(playController.getPiece(new Coordinate(2, 3)));
    }

    @Test
    public void testGivenPlayControllerWhenPieceCanEatAndWillNotEatWhitesDouble() {
        Game game = new GameBuilder().rows(
            " n      ",
            "        ",
            "        ",
            "    n n ",
            "        ",
            "b   b   ",
            "        ",
            "      b ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(5, 4);
        Coordinate target = new Coordinate(4, 5);
        playController.move(origin, target);
        origin = new Coordinate(0, 1);
        target = new Coordinate(1, 2);
        playController.move(origin, target);
        assertTrue(playController.getPiece(new Coordinate(3, 4)) == null
            || playController.getPiece(new Coordinate(3, 6)) == null);
    }

    @Test
    public void testGivenPlayControllerWhenPieceCanEatAndWillNotEatBlacksDouble() {
        Game game = new GameBuilder().rows(
            " n      ",
            "     n  ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            " b b    ",
            "  b b b ").build();
        playController = new PlayController(game, new State());
        Coordinate origin = new Coordinate(7, 6);
        Coordinate target = new Coordinate(6, 7);
        playController.move(origin, target);
        assertTrue(playController.getPiece(new Coordinate(6, 1)) == null
            || playController.getPiece(new Coordinate(6, 3)) == null);
    }


}
