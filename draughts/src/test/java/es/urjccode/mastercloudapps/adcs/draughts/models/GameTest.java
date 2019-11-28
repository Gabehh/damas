package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        this.game = new Game();
    }

    @Test
    public void testGivenBoardWhenIsNewThenPrefixedLocations() {
        this.game = new GameBuilder().rows(
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "b b b b ",
            " b b b b",
            "b b b b ").build();
        assertEquals(game, new Game());
    }

    @Test
    public void testGivenGameWhenIsCorrectWithEmptyOriginThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.EMPTY_ORIGIN, this.game.isCorrect(new Coordinate(4, 3), new Coordinate(3, 4)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteOppositePieceThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.OPPOSITE_PIECE, this.game.isCorrect(new Coordinate(2, 1), new Coordinate(3, 0)));
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
        assertEquals(Error.OPPOSITE_PIECE, this.game.isCorrect(new Coordinate(7, 2), new Coordinate(6, 3)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotDiagonalThenError() {
        game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, this.game.isCorrect(new Coordinate(5, 2), new Coordinate(4, 2)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotDiagonalThenError() {
        game = new GameBuilder().onBlack().rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_DIAGONAL, this.game.isCorrect(new Coordinate(0, 1), new Coordinate(1, 1)));
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
        assertEquals(Error.NOT_ADVANCED, this.game.isCorrect(new Coordinate(4, 7), new Coordinate(5, 6)));
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
        assertEquals(Error.NOT_ADVANCED, this.game.isCorrect(new Coordinate(1, 0), new Coordinate(0, 1)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotEmptyTargeThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "      n ",
            "       b",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(4, 7), new Coordinate(3, 6)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotEmptyTargeThenError() {
        this.game = new GameBuilder().onBlack().rows(
            " n      ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(0, 1), new Coordinate(1, 0)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackEatingAndNotEmptyTargeThenError() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(4, 1), new Coordinate(2, 3)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteEatingAndNotEmptyTargeThenError() {
        this.game = new GameBuilder().onBlack().rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ").build();
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(3, 0), new Coordinate(5, 2)));
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
        assertEquals(Error.EATING_EMPTY, this.game.isCorrect(new Coordinate(5, 4), new Coordinate(3, 2)));
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
        assertEquals(Error.BAD_DISTANCE, this.game.isCorrect(new Coordinate(2, 1), new Coordinate(5, 4)));
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
        assertEquals(Error.BAD_DISTANCE, this.game.isCorrect(new Coordinate(5, 0), new Coordinate(2, 3)));
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
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMoveWithBlackCorrectMovementThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "   n    ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ").build();
        this.game.move(new Coordinate(5, 0), new Coordinate(4, 1));
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
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
        this.game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.betweenDiagonal(target)));
        assertEquals(Color.WHITE, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteEatingThenOk() {
        this.game = new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ").build();
        this.game.move(new Coordinate(5,2), new Coordinate (4,3));
        Coordinate origin = new Coordinate(3, 0); 
        Coordinate target = new Coordinate(5, 2);
        this.game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.betweenDiagonal(target)));
        assertEquals(Color.BLACK, game.getColor(target));
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
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        assertTrue(this.game.getPiece(target) instanceof Draught);
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        this.game =  new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ",
            "   n    ",
            "        ").build();
        this.game.move(new Coordinate(3,0), new Coordinate(2,1));
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
        assertTrue(this.game.getPiece(target) instanceof Draught);
    }

}