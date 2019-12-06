package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    static String[] CHARACTERS = {"B", "N"};

    Draught(Color color) {
        super(color);
    }
    
    @Override
    protected String[] getCodes(){
		return Draught.CHARACTERS;
    }

    @Override
    Error isCorrectMovement(Piece between, int pair, Coordinate... coordinates) {
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		return null;
	}
    
}