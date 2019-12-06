package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece {

	protected Color color;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrectMovement(int betweenDiagonalPieces, int pair, Coordinate... coordinates){
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		if (!coordinates[pair].isOnDiagonal(coordinates[pair + 1]))
			return Error.NOT_DIAGONAL;
		return this.isCorrectDiagonalMovement(betweenDiagonalPieces, pair, coordinates);
	}

	abstract Error isCorrectDiagonalMovement(int betweenDiagonalPieces, int pair, Coordinate... coordinates);

	boolean isLimit(Coordinate coordinate) {
		return coordinate.isFirst() && this.getColor() == Color.WHITE
				|| coordinate.isLast() && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE)
			return difference > 0;
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return ""+this.getCodes()[this.color.ordinal()];
	}

	abstract protected char[] getCodes();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		return true;
	}

}