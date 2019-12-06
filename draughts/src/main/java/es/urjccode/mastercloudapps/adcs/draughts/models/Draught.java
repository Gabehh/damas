package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    private static String[] CHARACTERS = {"B", "N"};

    Draught(Color color) {
        super(color);
    }
    
    @Override
    protected String[] getCodes(){
		return Draught.CHARACTERS;
    }
    
    @Override
    Error isCorrectMovement(Coordinate origin, Coordinate target, Piece between) {
		assert origin != null;
		assert target != null;
		return null;
	}
    
}