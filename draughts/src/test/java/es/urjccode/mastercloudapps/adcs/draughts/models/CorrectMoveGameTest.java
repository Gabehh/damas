package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorrectMoveGameTest {

    private Game game;

    @Test
    public void testGivenGameWhenIsNewThenPrefixedLocations() {
        this.game = new GameBuilder().rows(
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "b b b b ",
            " b b b b",
            "b b b b ").build();
        assertEquals(this.game, new GameBuilder().build());
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteCorrectMovementThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ").build();
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        assertNull(this.game.move(origin, target));
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithBlackCorrectMovementThenOk() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "   n    ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ").build();
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(3, 4);
        assertNull(this.game.move(origin, target));
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithBlackEatingThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        Coordinate origin = new Coordinate(4, 1); 
        Coordinate target = new Coordinate(2, 3);
        assertNull(this.game.move(origin, target));
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.getBetweenDiagonalCoordinate(target)));
        assertEquals(Color.WHITE, game.getColor(target));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteEatingThenOk() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        Coordinate origin = new Coordinate(3, 0); 
        Coordinate target = new Coordinate(5, 2);
        assertNull(this.game.move(origin, target));
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.getBetweenDiagonalCoordinate(target)));
        assertEquals(Color.BLACK, game.getColor(target));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithBlackTwoEatingThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "    n   ",
            "        ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        Coordinate first = new Coordinate(4, 1); 
        Coordinate second = new Coordinate(2, 3);
        Coordinate third = new Coordinate(0, 5);
        assertNull(this.game.move(first, second, third));
        assertNull(game.getColor(first));
        assertNull(game.getColor(first.getBetweenDiagonalCoordinate(second)));
        assertNull(game.getColor(second));
        assertNull(game.getColor(second.getBetweenDiagonalCoordinate(third)));
        assertEquals(Color.WHITE, game.getColor(third));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteTwoEatingThenOk() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "        ",
            "   b    ",
            "        ").build();
        Coordinate first = new Coordinate(3, 0); 
        Coordinate second = new Coordinate(5, 2);
        Coordinate third = new Coordinate(7, 4);
        assertNull(this.game.move(first, second, third));
        assertNull(game.getColor(first));
        assertNull(game.getColor(first.getBetweenDiagonalCoordinate(second)));
        assertNull(game.getColor(second));
        assertNull(game.getColor(second.getBetweenDiagonalCoordinate(third)));
        assertEquals(Color.BLACK, game.getColor(third));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameW333henMoveWithBlackTwoEatingThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        Coordinate first = new Coordinate(4, 1); 
        Coordinate second = new Coordinate(3, 2);
        Coordinate third = new Coordinate(2, 3);
        assertEquals(Error.TOO_MUCH_JUMPS, this.game.move(first, second, third));
        assertEquals(Color.WHITE, game.getColor(first));
        assertNull(game.getColor(second));
        assertNull(game.getColor(third));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        this.game = new GameBuilder().rows(
            "        ",
            "b       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        assertNull(this.game.move(origin, target));
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        assertTrue(this.game.getPiece(target) instanceof Draught);
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        this.game =  new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ",
            "   n    ",
            "        ").build();
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        assertNull(this.game.move(origin, target));
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
        assertTrue(this.game.getPiece(target) instanceof Draught);
        assertEquals(Color.WHITE, this.game.getColor());
    }

}