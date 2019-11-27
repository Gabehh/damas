package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        this.game = new Game();
    }

    @Test
    public void testGivenBoardWhenIsNewThenPrefixedLocations() {
        for (int i = 0; i < this.game.getDimension(); i++) {
            for (int j = 0; j < this.game.getDimension(); j++) {
                final Coordinate coordinate = new Coordinate(i, j);
                final Color color = this.game.getColor(coordinate);
                if (coordinate.isBlack()){
                    if (i < 3) {
                        assertEquals(Color.BLACK, color);
                    } else if (i> 4) {
                        assertEquals(Color.WHITE, color);
                    } else {
                        assertNull(color);
                    }
                }
                
            }
        }
    }

    @Test
    public void testGivenGameWhenIsCorrectWithEmptyOriginThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertEquals(Error.EMPTY_ORIGIN, this.game.isCorrect(new Coordinate(4, 3), new Coordinate(3, 4)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithOppositePieceThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertEquals(Error.OPPOSITE_PIECE, this.game.isCorrect(new Coordinate(2, 1), new Coordinate(3, 0)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotDiagonalThenError() {
        game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertEquals(Error.NOT_DIAGONAL, this.game.isCorrect(new Coordinate(5, 2), new Coordinate(4, 2)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotDiagonalThenError() {
        game = GameBuilder.rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        this.game.move(new Coordinate(5,2), new Coordinate(4,3));
        assertEquals(Error.NOT_DIAGONAL, this.game.isCorrect(new Coordinate(0, 1), new Coordinate(1, 1)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotAdvancedThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertEquals(Error.NOT_ADVANCED, this.game.isCorrect(new Coordinate(4, 7), new Coordinate(5, 6)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotAdvancedThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "n       ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ");
        this.game.move(new Coordinate(4,7), new Coordinate(3,6));
        assertEquals(Error.NOT_ADVANCED, this.game.isCorrect(new Coordinate(1, 0), new Coordinate(0, 1)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotEmptyTargeThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "      n ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(4, 7), new Coordinate(3, 6)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotEmptyTargeThenError() {
        this.game = GameBuilder.rows(
            " n      ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ");
        this.game.move(new Coordinate(7,0), new Coordinate(6,1));
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(0, 1), new Coordinate(1, 0)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackEatingAndNotEmptyTargeThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "   n    ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(4, 1), new Coordinate(2, 3)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteEatingAndNotEmptyTargeThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b b   ",
            "        ",
            "        ");
        this.game.move(new Coordinate(5,4), new Coordinate (4,5));
        assertEquals(Error.NOT_EMPTY_TARGET, this.game.isCorrect(new Coordinate(3, 0), new Coordinate(5, 2)));
    }


    @Test
    public void testGivenGameWhenIsCorrectWithEatingEmptyThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        assertEquals(Error.EATING_EMPTY, this.game.isCorrect(new Coordinate(5, 4), new Coordinate(3, 2)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackBadDistanceThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        this.game.move(new Coordinate(5,0), new Coordinate (4,1));
        assertEquals(Error.BAD_DISTANCE, this.game.isCorrect(new Coordinate(2, 1), new Coordinate(5, 4)));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteBadDistanceThenError() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        assertEquals(Error.BAD_DISTANCE, this.game.isCorrect(new Coordinate(5, 0), new Coordinate(2, 3)));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteCorrectMovementThenOk() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMoveWithBlackCorrectMovementThenOk() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "   n    ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        this.game.move(new Coordinate(5, 0), new Coordinate(4, 1));
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMoveWithBlackEatingThenOk() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ");
        Coordinate origin = new Coordinate(4, 1); 
        Coordinate target = new Coordinate(2, 3);
        this.game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.betweenDiagonal(target)));
        assertEquals(Color.WHITE, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteEatingThenOk() {
        this.game = GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ");
        this.game.move(new Coordinate(5,2), new Coordinate (4,3));
        Coordinate origin = new Coordinate(3, 0); 
        Coordinate target = new Coordinate(5, 2);
        this.game.move(origin, target);
        assertNull(game.getColor(origin));
        assertNull(game.getColor(origin.betweenDiagonal(target)));
        assertEquals(Color.BLACK, game.getColor(target));
    }

}