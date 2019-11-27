package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameBuilderTest {

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsNumberThenError() {
        GameBuilder.rows(
            "        ",
            "        ",
 //           "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectCharactersThenError() {
        GameBuilder.rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "   x    ",
            "        ",
            "        ",
            "        ");
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsLengthThenError() {
        GameBuilder.rows(
            "         ",
            "       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
    }

    @Test
    public void testGivenGameBuilderWhenCorrectRowsThenOk() {
        Game game = GameBuilder.rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ");
        assertNull(game.getColor(new Coordinate(0, 0)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(0, 1)));
        assertEquals(Color.WHITE, game.getColor(new Coordinate(7, 0)));
        assertNull(game.getColor(new Coordinate(7, 1)));
    }

}