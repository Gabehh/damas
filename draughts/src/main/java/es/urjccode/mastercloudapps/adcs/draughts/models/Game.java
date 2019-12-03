package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Board board;
	private Turn turn;

	Game(Board board) {
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

	public Error move(Coordinate first, Coordinate second, Coordinate third) {
		assert first != null;
		assert second != null;
		Error error = this.move(first, second);
		if (error != null)
			return error;
		if (third != null) {
			error = this.move(second, third);
			if (error != null)
				this.unmove(third, second);
				return error;
		}
		return null;
	}

	Error move(Coordinate origin, Coordinate target) {
		Error error = this.isCorrectMovement(origin, target);
		if (error != null) {
			return error;
		}
		if (origin.getDiagonalDistance(target) == 2) {
			this.board.remove(origin.getBetweenDiagonalCoordinate(target));
		}
		this.board.move(origin, target);
		if (this.board.getPiece(target).isLimit(target)) {
			Color color = this.board.getColor(target);
			this.board.remove(target);
			this.board.put(target, new Draught(color));
		}
		this.turn.change();
		return null;
	}

	Error isCorrectMovement(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		if (!origin.isOnDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!this.board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		Piece between = this.board.getBetweenDiagonalPiece(origin, target);
		return this.board.getPiece(origin).isCorrectMovement(origin, target, between);
	}

	void unmove(Coordinate target, Coordinate origin) {
		this.board.move(target, origin);
		this.turn.change();
	}

	public boolean isBlocked() {
		for(Coordinate coordinate : this.getCoordinatesWithActualColor()){
			if (!this.isBlocked(coordinate)){
				return false;
			}
		}
		return true;
	}

	private List<Coordinate> getCoordinatesWithActualColor(){
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		for (int i = 0; i < this.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getPiece(coordinate);
				if (piece != null && piece.getColor() == this.getColor()) {
					coordinates.add(coordinate);
				}
			}
		}
		return coordinates;
	}

	boolean isBlocked(Coordinate coordinate) {
		for (int i = 1; i <= 2; i++) {
			for (Coordinate target : coordinate.getDiagonalCoordinates(i)) {
				if (this.isCorrectMovement(coordinate, target) == null) {
					return false;
				}
			}
		}
		return true;
	}

	public void cancel() {
		for(Coordinate coordinate : this.getCoordinatesWithActualColor()){
			this.board.remove(coordinate);
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

	Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	public int getDimension() {
		return this.board.getDimension();
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