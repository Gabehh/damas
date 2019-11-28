package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class GameBuilder {

    private boolean onBlack;
    private List<String> strings;

    GameBuilder() {
        this.onBlack = false;
        this.strings = new ArrayList<String>();
    }

    GameBuilder onBlack() {
        this.onBlack = true;
        return this;
    }

    GameBuilder rows(String... strings) {
        for (String string : strings) {
            assert Pattern.matches("[bn ]{8}", string);
            this.strings.add(string);
        }
        return this;
    }

    Game build() {
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
        if (this.onBlack) {
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