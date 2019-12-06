package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawn extends Piece {

	private static final int MAX_DISTANCE = 2;
    private static char[] CHARACTERS = {'b', 'n'};

    Pawn(Color color) {
        super(color);
    }

    @Override
    Error isCorrectDiagonalMovement(int betweenDiagonalPieces, int pair, Coordinate... coordinates) {
		if (!this.isAdvanced(coordinates[pair], coordinates[pair+1])) 
			return Error.NOT_ADVANCED;
		int distance = coordinates[pair].getDiagonalDistance(coordinates[pair+1]);
		if (distance > Pawn.MAX_DISTANCE) 
			return Error.TOO_MUCH_ADVANCED;
		if (distance == Pawn.MAX_DISTANCE && betweenDiagonalPieces != 1) 
			return Error.WITHOUT_EATING;
		return null;
    }
    
    protected char[] getCodes() {
		return Pawn.CHARACTERS;
	}
    
}