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
		this.reset();
	}

	public void reset() {
		for (int i = 0; i < Coordinate.getDimension(); i++)
			for (int j = 0; j < Coordinate.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Color color = Color.getInitialColor(coordinate);
				Piece piece = null;
				if (color != null)
					piece = new Pawn(color);
				this.board.put(coordinate, piece);
			}
		if (this.turn.getColor() != Color.WHITE)
			this.turn.change();
	}

    public Error move(Coordinate... coordinates) {
        Error error = null;
        List<Coordinate> removedCoordinates = new ArrayList<Coordinate>();
        List<Coordinate> piecesCanEat = this.getPiecesCanEat();
        int pair = 0;
        do {
            error = this.board.isCorrectPairMove(turn,pair, coordinates);
            if (error == null) {
                this.board.pairMove(removedCoordinates, pair, coordinates);
                pair++;
            }
        } while (pair < coordinates.length - 1 && error == null);
        error = this.board.isCorrectGlobalMove(error, removedCoordinates, coordinates);
        if (error == null) {
            this.board.removePieceRandom(piecesCanEat, removedCoordinates.size(), coordinates);
            this.turn.change();
        }
        else
            this.unMovesUntilPair(removedCoordinates, pair, coordinates);
        return error;
    }

    private List<Coordinate> getPiecesCanEat()
    {
        List<Coordinate> coordinateList =  this.board.getCoordinatesWithActualColor(this.getTurnColor());
        List<Coordinate> listPieceEat = new ArrayList<>();
        for(Coordinate coordinate : coordinateList) {
            if(this.isCanEat(coordinate))
                listPieceEat.add(coordinate);
        }
        return listPieceEat;
    }
    public Boolean isCanEat(Coordinate coordinate)
    {
        if(coordinate.getRow()+2<=Coordinate.getUpperLimit() && coordinate.getColumn()+2<=Coordinate.getUpperLimit())
            if(checkEat(coordinate, new Coordinate(coordinate.getRow()+2,coordinate.getColumn()+2)))
                return  true;
        if(coordinate.getRow()+2<=Coordinate.getUpperLimit() && coordinate.getColumn()-2>=Coordinate.getLowerLimit())
            if(checkEat(coordinate, new Coordinate(coordinate.getRow()+2,coordinate.getColumn()-2)))
                return  true;
        if(coordinate.getRow()-2>=Coordinate.getLowerLimit() && coordinate.getColumn()+2<=Coordinate.getUpperLimit())
            if(checkEat(coordinate, new Coordinate(coordinate.getRow()-2,coordinate.getColumn()+2)))
                return  true;
        if(coordinate.getRow()-2>=Coordinate.getLowerLimit() && coordinate.getColumn()-2>=Coordinate.getLowerLimit())
            if(checkEat(coordinate, new Coordinate(coordinate.getRow()-2,coordinate.getColumn()-2)))
                return  true;
        return false;
    }

    public Boolean checkEat(Coordinate... coordinates)
    {
        return this.board.isCorrectPairMove(turn,0,coordinates) == null && this.board.getBetweenDiagonalPiece(0, coordinates) != null;
    }

	private void unMovesUntilPair(List<Coordinate> removedCoordinates, int pair, Coordinate... coordinates) {
		for (int j = pair; j > 0; j--)
			this.board.move(coordinates[j], coordinates[j - 1]);
		for (Coordinate removedPiece : removedCoordinates)
			this.board.put(removedPiece, new Pawn(this.getOppositeTurnColor()));
	}

	public boolean isBlocked() {
		for (Coordinate coordinate : this.board.getCoordinatesWithActualColor(this.getTurnColor()))
			if (!this.isBlocked(coordinate))
				return false;
		return true;
	}

	private boolean isBlocked(Coordinate coordinate) {
		for (int i = 1; i <= 2; i++)
			for (Coordinate target : coordinate.getDiagonalCoordinates(i))
				if (this.board.isCorrectPairMove(turn,0, coordinate, target) == null)
					return false;
		return true;
	}

	public void cancel() {
		for (Coordinate coordinate : this.board.getCoordinatesWithActualColor(this.getTurnColor()))
			this.board.remove(coordinate);
		this.turn.change();
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getTurnColor() {
		return this.turn.getColor();
	}

	private Color getOppositeTurnColor() {
		return this.turn.getOppositeColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	public int getDimension() {
		return Coordinate.getDimension();
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
