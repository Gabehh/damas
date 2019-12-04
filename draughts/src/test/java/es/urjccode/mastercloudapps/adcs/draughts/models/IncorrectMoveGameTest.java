package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IncorrectMoveGameTest {

    private Game game;
    private Color color;
    private String[] strings;

    private void setGame(Color color, String... strings) {
        this.color = color;
        this.strings = strings;
        this.game = new GameBuilder().setColor(color).rows(strings).build();
    }

    private void assertErrorMove(Error error, Coordinate... coordinates) {
        assertEquals(error, this.game.move(coordinates));
        assertEquals(this.game, new GameBuilder().setColor(color).rows(strings).build());
    }

    @Test
    public void testGivenGameWhenMoveWithEmptyWhiteOriginThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertErrorMove(Error.EMPTY_ORIGIN, 
            new Coordinate(4, 3), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveWithEmptyBlackOriginThenError() {
        this.setGame(Color.BLACK, 
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ");
        assertErrorMove(Error.EMPTY_ORIGIN, 
            new Coordinate(4, 3), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteOppositePieceThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ");
        assertErrorMove(Error.OPPOSITE_PIECE, 
            new Coordinate(2, 1), 
            new Coordinate(3, 0));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackOppositePieceThenError() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "  n     ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ");
        assertErrorMove(Error.OPPOSITE_PIECE,
            new Coordinate(7, 2), 
            new Coordinate(6, 3));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithUpMovementThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(5, 2), 
            new Coordinate(4, 2));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithDownMovementThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(5, 2), 
            new Coordinate(6, 2));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithRightMovementThenError() {
        this.setGame(Color.BLACK, 
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL, 
            new Coordinate(0, 1), 
            new Coordinate(0, 2));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithLeftMovementThenError() {
        this.setGame(Color.BLACK,
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(0, 1), 
            new Coordinate(0, 0));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithNotEmptyTargeWhiteThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "      n ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(4, 7), 
            new Coordinate(3, 6));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithNotEmptyTargetBlackThenError() {
        this.setGame(Color.BLACK,
            " n      ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(0, 1), 
            new Coordinate(1, 0));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteEatingAndNotEmptyTargeThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "   n    ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(4, 1), 
            new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackEatingAndNotEmptyTargeThenError() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(3, 0), 
            new Coordinate(5, 2));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteNotAdvancedThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_ADVANCED,
            new Coordinate(4, 7), 
            new Coordinate(5, 6));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackNotAdvancedThenError() {
        this.setGame(Color.BLACK,
            "        ",
            "n       ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_ADVANCED,
            new Coordinate(1, 0), 
            new Coordinate(0, 1));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithWhiteBadDistanceThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_ADVANCED,
            new Coordinate(5, 0), 
            new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithBlackBadDistanceThenError() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_ADVANCED,
            new Coordinate(2, 1), 
            new Coordinate(5, 4));
    }

    @Test
    public void testGivenGameWhenIsCorrectWithEatingEmptyThenError() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        assertErrorMove(Error.WITHOUT_EATING,
            new Coordinate(5, 4), 
            new Coordinate(3, 2));
    }

    @Test
    public void testGivenGameWhenIsCorrectBlackWithEatingEmptyThenError() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "  n     ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        assertErrorMove(Error.WITHOUT_EATING,
            new Coordinate(3, 2), 
            new Coordinate(5, 0));
    }

    @Test
    public void testGivenGameW333henMoveWithBlackTwoEatingThenOk() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_JUMPS,
            new Coordinate(4, 1), 
            new Coordinate(3, 2), 
            new Coordinate(2, 3));
    }

}