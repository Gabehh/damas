package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

	private Board board;
	private Turn turn;

	Game(Board board){
		this.turn = new Turn();
		this.board = board;
	}

	public Game() {
		this(new Board());
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Color color = Color.getInitialColor(coordinate);
				if (color != null) {
					this.board.put(coordinate, new Piece(color));
				}
			}
		}
	}

	public Error isCorrect(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		return this.board.getPiece(origin).isCorrect(origin, target, board);
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.isCorrect(origin, target) == null;
		if (origin.diagonalDistance(target) == 2) {
			this.board.remove(origin.betweenDiagonal(target));
		}
		this.board.move(origin, target);
		if (this.board.getPiece(target).isLimit(target)){
			Color color = this.board.getColor(target);
			this.board.remove(target);
			this.board.put(target, new Draught(color));
		}
		this.turn.change();
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
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
		Game other = (Game) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}

}