package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Board {

    static final int DIMENSION = 8;

    private Square[][] squares;

    Board() {
        this.squares = new Square[Board.DIMENSION][Board.DIMENSION];
        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                this.squares[i][j] = new Square();
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                if (new Coordinate(i, j).isBlack()) {
                    this.squares[i][j].put(new Piece(Color.BLACK));
                }
            }
        }
        for (int i = 5; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                if (new Coordinate(i, j).isBlack()) {
                    this.squares[i][j].put(new Piece(Color.WHITE));
                }
            }
        }
    }

    public void move(Coordinate origin, Coordinate target) {
        Piece piece = this.squares[origin.getRow()][origin.getColumn()].remove();
        this.squares[target.getRow()][target.getColumn()].put(piece);
    }

    public void remove(Coordinate coordinate) {
        assert coordinate != null;
        assert this.getPiece(coordinate) != null;
        this.squares[coordinate.getRow()][coordinate.getColumn()].remove();
    }

    public Color getColor(Coordinate coordinate) {
        return this.squares[coordinate.getRow()][coordinate.getColumn()].getColor();
    }

    public Piece getPiece(Coordinate coordinate) {
        return this.squares[coordinate.getRow()][coordinate.getColumn()].getPiece();
    }

    public List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<Piece>();
        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                pieces.add(this.squares[i][j].getPiece());
            }
        }
		return pieces;
	}

    boolean isEmpty(Coordinate coordinate) {
        return this.squares[coordinate.getRow()][coordinate.getColumn()].isEmpty();
    }
    
    public int getDimension() {
		return Board.DIMENSION;
	}

    @Override
    public String toString() {
        String string = " ";
        for (int j = 0; j < Board.DIMENSION; j++) {
            string += j;
        }
        string += "\n";
        for (int i = 0; i < Board.DIMENSION; i++) {
            string += i;
            for (int j = 0; j < Board.DIMENSION; j++) {
                Piece piece = this.squares[i][j].getPiece();
                if (piece == null) {
                    string += " ";
                } else if (piece.isBlack()) {
                    string += "n";
                } else {
                    string += "b";
                }
            }
            string += i + "\n";
        }
        string += " ";
        for (int j = 0; j < Board.DIMENSION; j++) {
            string += j;
        }
        return string;
    }

}