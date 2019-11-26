package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameBuilderTest {

    @Test
    public void testGivenGameBuilderWhenCorrectRowsThenOk() {
        Game game = new GameBuilder()
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row(" n      ")
            .build();
        assertEquals(Color.WHITE, game.getColor(new Coordinate(0, 0)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(7, 1)));
        assertNull(game.getColor(new Coordinate(1, 2)));
        assertNull(game.getColor(new Coordinate(7, 0)));
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsNumberThenError() {
        new GameBuilder()
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
//            .row(" n      ")
            .build();
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectCharactersThenError() {
        new GameBuilder()
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("   x    ")
            .row("        ")
            .row("        ")
            .row(" n      ")
            .build();
    }

    @Test(expected = AssertionError.class)
    public void testGivenGameBuilderWhenIncorrectRowsLengthThenError() {
        new GameBuilder()
            .row("b        ")
            .row("       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row(" n      ")
            .build();
    }

}