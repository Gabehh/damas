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
                    this.getSquare(new Coordinate(i,j)).put(new Piece(Color.BLACK));
                }
            }
        }
        for (int i = 5; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                if (new Coordinate(i, j).isBlack()) {
                    this.getSquare(new Coordinate(i,j)).put(new Piece(Color.WHITE));
                }
            }
        }
    }

    private Square getSquare(Coordinate coordinate){
        assert coordinate!=null && coordinate.isValid();
        return this.squares[coordinate.getRow()][coordinate.getColumn()];
    }

    public void put(Coordinate coordinate, Piece piece){
        assert piece != null;
        this.getSquare(coordinate).put(piece);
    }

    public Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        return this.getSquare(coordinate).remove();
    }

    public void move(Coordinate origin, Coordinate target) {
        this.put(target, this.remove(origin));
    }

    public Piece getPiece(Coordinate coordinate) {
        return this.getSquare(coordinate).getPiece();
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.getSquare(coordinate).isEmpty();
    }
    
    public Color getColor(Coordinate coordinate) {
        return this.getSquare(coordinate).getColor();
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