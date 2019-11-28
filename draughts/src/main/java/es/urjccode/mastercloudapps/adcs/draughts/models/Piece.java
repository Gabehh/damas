package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;
	private static final int MAX_DISTANCE = 2;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrectMovement(Coordinate origin, Coordinate target, Piece between) {
		assert origin != null;
		assert target != null;
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		int distance = origin.diagonalDistance(target);
		if (distance > Piece.MAX_DISTANCE) {
			return Error.TOO_MUCH_ADVANCED;
		}
		if (distance == Piece.MAX_DISTANCE && between == null) {
			return Error.WITHOUT_EATING;
		}
		return null;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.isFirst() && this.getColor() == Color.WHITE ||
		coordinate.isLast() && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

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