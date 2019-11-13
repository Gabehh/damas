package es.urjccode.mastercloudapps.adcs.draughts.models;

class Square {

    private Piece piece;

    Square() {
    }

    void put(Piece piece) {
        assert piece != null;
        this.piece = piece;
    }

    Piece remove() {
        Piece removedPiece = this.getPiece();
        this.piece = null;
        return removedPiece;
    }

	boolean isEmpty() {
		return this.getPiece() == null;
	}

	Color getColor() {
        if (this.getPiece() == null){
            return null;
        }
		return this.getPiece().getColor();
    }
    
    Piece getPiece() {
        return this.piece;
    }

}