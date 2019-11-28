package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameBuilderTest {

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsNumberThenError() {
        new GameBuilder().rows(
            "        ",
            "        ",
 //           "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectCharactersThenError() {
        new GameBuilder().rows(
            "        ",
            "        ",
            "        ",
            "        ",
            "   x    ",
            "        ",
            "        ",
            "        ").build();
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsLengthThenError() {
        new GameBuilder().rows(
            "         ",
            "       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ").build();
    }

    @Test
    public void testGivenGameBuilderWhenCorrectRowsThenOk() {
        Game game = new GameBuilder().rows(
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ").build();
        assertNull(game.getColor(new Coordinate(0, 0)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(0, 1)));
        assertEquals(Color.WHITE, game.getColor(new Coordinate(7, 0)));
        assertNull(game.getColor(new Coordinate(7, 1)));
    }

}