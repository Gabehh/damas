package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class GameTest {

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
    public void testGivenGameWhenMoveWithEmptyWhiteOriginThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.EMPTY_ORIGIN, 
            this.game.move(new Coordinate(4, 3), new Coordinate(3, 4)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithEmptyBlackOriginThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.EMPTY_ORIGIN, 
            this.game.move(new Coordinate(4, 3), new Coordinate(3, 4)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteOppositePieceThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ").build();
        assertEquals(Error.OPPOSITE_PIECE, 
            this.game.move(new Coordinate(2, 1),new Coordinate(3, 0)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackOppositePieceThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "  n     ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ").build();
        assertEquals(Error.OPPOSITE_PIECE, 
            this.game.move(new Coordinate(7, 2), new Coordinate(6, 3)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithUpMovementThenError() {
        game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, 
            this.game.move(new Coordinate(5, 2), new Coordinate(4, 2)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithDownMovementThenError() {
        game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, 
            this.game.move(new Coordinate(5, 2), new Coordinate(6, 2)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithRightMovementThenError() {
        game = new GameBuilder().onBlack().rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, 
            this.game.move(new Coordinate(0, 1), new Coordinate(0, 2)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithLeftMovementThenError() {
        game = new GameBuilder().onBlack().rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, 
            this.game.move(new Coordinate(0, 1), new Coordinate(0, 0)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithNotEmptyTargeWhiteThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "      n ",
            "       b",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, 
            this.game.move(new Coordinate(4, 7), new Coordinate(3, 6)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithNotEmptyTargetBlackThenError() {
        this.game = new GameBuilder().onBlack().rows(
            " n      ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, 
            this.game.move(new Coordinate(0, 1), new Coordinate(1, 0)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteEatingAndNotEmptyTargeThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, 
            this.game.move(new Coordinate(4, 1), new Coordinate(2, 3)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackEatingAndNotEmptyTargeThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, 
            this.game.move(new Coordinate(3, 0), new Coordinate(5, 2)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotAdvancedThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_ADVANCED, 
            this.game.move(new Coordinate(4, 7), new Coordinate(5, 6)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotAdvancedThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "n       ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_ADVANCED, 
            this.game.move(new Coordinate(1, 0), new Coordinate(0, 1)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteBadDistanceThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ").build();
        assertEquals(Error.TOO_MUCH_ADVANCED, 
            this.game.move(new Coordinate(5, 0), new Coordinate(2, 3)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackBadDistanceThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ").build();
        assertEquals(Error.TOO_MUCH_ADVANCED, 
            this.game.move(new Coordinate(2, 1), new Coordinate(5, 4)));
        assertEquals(Color.BLACK, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithEatingEmptyThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    b   ",
            "        ",
            "        ").build();
        assertEquals(Error.WITHOUT_EATING, 
            this.game.move(new Coordinate(5, 4), new Coordinate(3, 2)));
        assertEquals(Color.WHITE, this.game.getColor());
    }

    @Test
    public void testGivenGameWhenIsCorrectBlackWithEatingEmptyThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "  n     ",
            "        ",
            "    b   ",
            "        ",
            "        ").build();
        assertEquals(Error.WITHOUT_EATING, 
            this.game.move(new Coordinate(3, 2), new Coordinate(5, 0)));
        assertEquals(Color.BLACK, this.game.getColor());
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

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteEmptyPiecesThenTrue(){
        this.game =  new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackEmptyPiecesThenTrue(){
        this.game =  new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteOnePieceThenTrue(){
        this.game =  new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            " n      ",
            "b       ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackOnePieceThenTrue(){
        this.game =  new GameBuilder().onBlack().rows(
            " n      ",
            "b b     ",
            "   b    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteTwoPieceThenTrue(){
        this.game =  new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "n n n   ",
            " n n    ",
            "b b     ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackTwoPieceThenTrue(){
        this.game =  new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "n n n   ",
            " n n    ",
            "b b     ").build();
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedThenFalse(){
        this.game =  new GameBuilder().rows(
            "        ",
            " b      ",
            "        ",
            "        ",
            " n   n  ",
            "        ",
            "   b    ",
            "        ").build();
        assertFalse(this.game.isBlocked());
    }
    
}