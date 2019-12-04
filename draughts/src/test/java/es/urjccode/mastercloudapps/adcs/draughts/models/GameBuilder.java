package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GameBuilder {

    private boolean onBlack;
    private Color color;
    private List<String> strings;

    public GameBuilder() {
        this.onBlack = false;
        this.color = null;
        this.strings = new ArrayList<String>();
    }

    GameBuilder setColor(Color color){
        this.color = color;
        return this;
    }

    GameBuilder onBlack() {
        this.onBlack = true;
        return this;
    }

    public GameBuilder rows(String... strings) {
        for (String string : strings) {
            assert Pattern.matches("[bn ]{8}", string);
            this.strings.add(string);
        }
        return this;
    }

    public Game build() {
        if (this.strings.size() == 0){
            assertFalse(this.onBlack);
            return new Game();
        }
        Board board = new Board();
        Game game = new Game(board);
        assert this.strings.size() == board.getDimension();
        this.setColor(game, board);
        for (int i = 0; i < this.strings.size(); i++) {
            this.setRow(board, i, this.strings.get(i));
        }
        return game;
    }

    private void setColor(Game game, Board board) {
        if (this.onBlack || this.color == Color.BLACK) {
            board.put(new Coordinate(7, 0), new Piece(Color.WHITE));
            game.move(new Coordinate(7, 0), new Coordinate(6, 1));
            board.remove(new Coordinate(6, 1));
        }
    }

    private void setRow(Board board, int row, String string) {
        for (int j = 0; j < string.length(); j++) {
            Color color = this.getColor(string.charAt(j));
            if (color != null) {
                board.put(new Coordinate(row, j), new Piece(color));
            }
        }
    }

    private Color getColor(char character) {
        switch (character) {
        case 'b':
            return Color.WHITE;
        case 'n':
            return Color.BLACK;
        default:
            return null;
        }
    }

}