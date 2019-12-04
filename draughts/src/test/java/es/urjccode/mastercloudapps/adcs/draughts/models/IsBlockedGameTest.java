package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class IsBlockedGameTest {

    private Game game;

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