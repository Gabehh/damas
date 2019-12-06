package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawn extends Piece {

    Pawn(Color color) {
        super(color);
    }

    @Override
    Error isCorrectMovement(Coordinate origin, Coordinate target, Piece between) {
		assert origin != null;
		assert target != null;
		if (!this.isAdvanced(origin, target)) 
			return Error.NOT_ADVANCED;
		int distance = origin.getDiagonalDistance(target);
		if (distance > Piece.MAX_DISTANCE) 
			return Error.TOO_MUCH_ADVANCED;
		if (distance == Piece.MAX_DISTANCE && between == null) 
			return Error.WITHOUT_EATING;
		return null;
	}
    
}