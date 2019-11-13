package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;

	Piece(Color color){
		assert color != null;
		this.color = color;
	}

	public boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE){
			return difference>0;
		}
		return difference<0;
	}
	
	public Color getColor() {
		return this.color;
	}

}