package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.Arrays;

class Board implements PieceProvider {

    private static final int DIMENSION = 8;
    private Piece[][] pieces;

    Board() {
        this.pieces = new Piece[this.getDimension()][this.getDimension()];
        for (int i = 0; i < this.getDimension(); i++)
            for (int j = 0; j < this.getDimension(); j++)
                this.pieces[i][j] = null;
    }

    @Override
    public Piece getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.pieces[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(Coordinate coordinate, Piece piece) {
        this.pieces[coordinate.getRow()][coordinate.getColumn()] = piece;
    }

    Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        Piece piece = this.getPiece(coordinate);
        this.put(coordinate, null);
        return piece;
    }

    void move(Coordinate origin, Coordinate target) {
        assert this.getColor(origin) != null;
        this.put(target, this.remove(origin));
    }

    @Override
    public boolean isEmpty(Coordinate coordinate) {
        return this.getPiece(coordinate) == null;
    }

    public Piece getBetweenDiagonalPiece(Coordinate origin, Coordinate target){
        Coordinate between = null;
        if (origin.isOnDiagonal(target) && origin.getDiagonalDistance(target) == 2)
            between = origin.getBetweenDiagonalCoordinate(target);
		if (between != null)
			return this.getPiece(between);
        return null;
    }

    Color getColor(Coordinate coordinate) {
        final Piece piece = this.getPiece(coordinate);
        if (piece == null)
            return null;
		return piece.getColor();
    }

    int getDimension() {
        return Board.DIMENSION;
    }

    @Override
    public String toString() {
        String string = "";
        string += this.toStringHorizontalNumbers();
        for (int i = 0; i < this.getDimension(); i++)
            string += this.toStringHorizontalPiecesWithNumbers(i);
        string += this.toStringHorizontalNumbers();
        return string;
    }

    private String toStringHorizontalNumbers() {
        String string = " ";
        for (int j = 0; j < this.getDimension(); j++)
            string += j;
        return string + "\n";
    }

    private String toStringHorizontalPiecesWithNumbers(int row) {
        String string = " " + row;
        for (int j = 0; j < this.getDimension(); j++) {
            Piece piece = this.getPiece(new Coordinate(row, j));
            if (piece == null)
                string += " ";
            else {
                //final String[] letters = { "b", "n" };
                string += piece; //letters[piece.getColor().ordinal()];
            }
        }
        return string + row + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pieces);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (!Arrays.deepEquals(pieces, other.pieces))
            return false;
        return true;
    }

}