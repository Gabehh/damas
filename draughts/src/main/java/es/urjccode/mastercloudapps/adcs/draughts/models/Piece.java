package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;

	Piece(Color color){
		assert color != null;
		this.color = color;
	}

	Error isCorrect(Coordinate origin, Coordinate target, Board board){
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		if (origin.diagonalDistance(target) >= 3) {
			return Error.BAD_DISTANCE;
		}
		if (!board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (origin.diagonalDistance(target) == 2) {
			Coordinate between = origin.betweenDiagonal(target);
			if (board.getPiece(between) == null) {
				return Error.EATING_EMPTY;
			}
		}
		return null;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE){
			return difference>0;
		}
		return difference<0;
	}
	
	Color getColor() {
		return this.color;
	}

}