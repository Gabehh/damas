package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawn extends Piece {

    static char[] CHARACTERS = {'b', 'n'};

    Pawn(Color color) {
        super(color);
    }

    @Override
    Error isCorrectMovement(Piece between, int pair, Coordinate... coordinates) {
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		if (!this.isAdvanced(coordinates[pair], coordinates[pair+1])) 
			return Error.NOT_ADVANCED;
		int distance = coordinates[pair].getDiagonalDistance(coordinates[pair+1]);
		if (distance > Piece.MAX_DISTANCE) 
			return Error.TOO_MUCH_ADVANCED;
		if (distance == Piece.MAX_DISTANCE && between == null) 
			return Error.WITHOUT_EATING;
		return null;
    }
    
    protected char[] getCodes() {
		return Pawn.CHARACTERS;
	}
    
}