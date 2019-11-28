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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
        Square other = (Square) obj;
        if (piece == null) {
            if (other.piece != null)
                return false;
        } else if (!piece.equals(other.piece))
            return false;
        return true;
    }

}