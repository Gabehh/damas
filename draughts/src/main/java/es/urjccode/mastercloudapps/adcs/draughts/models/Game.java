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
		for (int i = 0; i < this.board.getDimension(); i++)
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Color color = Color.getInitialColor(coordinate);
				if (color != null)
					this.board.put(coordinate, new Pawn(color));
			}
	}

	public Error move(Coordinate... coordinates) {
		Error error = null;
		List<Coordinate> removedCoordinates = new ArrayList<Coordinate>();
		int pair = 0;
		do {
			error = this.isCorrectMove(pair, coordinates);
			if (error == null) {
				this.move(removedCoordinates, pair, coordinates);
				pair++;
			}
		} while (pair < coordinates.length - 1 && error == null);
		if (error == null && coordinates.length > 2 && coordinates.length > removedCoordinates.size() + 1)
			error = Error.TOO_MUCH_JUMPS;
		if (error == null)
			this.turn.change();
		else
			this.unmove(removedCoordinates, pair, coordinates);
		return error;
	}

	Error isCorrectMove(int pair, Coordinate... coordinates) {
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		if (board.isEmpty(coordinates[pair]))
			return Error.EMPTY_ORIGIN;
		if (this.turn.getOppositeColor() == this.board.getColor(coordinates[pair]))
			return Error.OPPOSITE_PIECE;
		if (!this.board.isEmpty(coordinates[pair + 1]))
			return Error.NOT_EMPTY_TARGET;
		int betweenDiagonalPieces = this.board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
		return this.board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPieces, pair, coordinates);
	}

	private void move(List<Coordinate> removedPieces, int pair, Coordinate... coordinates) {
		Coordinate forRemoving = this.getBetweenDiagonalPiece(pair, coordinates);
		if (forRemoving != null) {
			removedPieces.add(0, forRemoving);
			this.board.remove(removedPieces.get(0));
		}
		this.board.move(coordinates[pair], coordinates[pair + 1]);
		if (this.board.getPiece(coordinates[pair + 1]).isLimit(coordinates[pair + 1])) {
			Color color = this.board.getColor(coordinates[pair + 1]);
			this.board.remove(coordinates[pair + 1]);
			this.board.put(coordinates[pair + 1], new Draught(color));
		}
	}

	private Coordinate getBetweenDiagonalPiece(int pair, Coordinate... coordinates) {
		assert coordinates[pair].isOnDiagonal(coordinates[pair + 1]);
		List<Coordinate> betweenCoordinates = coordinates[pair].getBetweenDiagonalCoordinates(coordinates[pair + 1]);
		if (betweenCoordinates.isEmpty())
			return null;
		for (Coordinate coordinate : betweenCoordinates) {
			if (this.getPiece(coordinate) != null)
				return coordinate;
		}
		return null;
	}

	private void unmove(List<Coordinate> removedCoordinates, int pair, Coordinate... coordinates) {
		for (int j = pair; j > 0; j--)
			this.board.move(coordinates[j], coordinates[j - 1]);
		for (Coordinate removedPiece : removedCoordinates)
			this.board.put(removedPiece, new Pawn(this.getOppositeColor()));
	}

	public boolean isBlocked() {
		for (Coordinate coordinate : this.getCoordinatesWithActualColor())
			if (!this.isBlocked(coordinate))
				return false;
		return true;
	}

	private List<Coordinate> getCoordinatesWithActualColor() {
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		for (int i = 0; i < this.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getPiece(coordinate);
				if (piece != null && piece.getColor() == this.getColor())
					coordinates.add(coordinate);
			}
		}
		return coordinates;
	}

	boolean isBlocked(Coordinate coordinate) {
		for (int i = 1; i <= 2; i++)
			for (Coordinate target : coordinate.getDiagonalCoordinates(i))
				if (this.isCorrectMove(0, coordinate, target) == null)
					return false;
		return true;
	}

	public void cancel() {
		for (Coordinate coordinate : this.getCoordinatesWithActualColor())
			this.board.remove(coordinate);
		this.turn.change();
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	private Color getOppositeColor() {
		return this.turn.getOppositeColor();
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