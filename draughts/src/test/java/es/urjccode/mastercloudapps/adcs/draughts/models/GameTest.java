package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        game = new Game();
    }

    @Test()
    public void testGivenGameWhenMoveWithOuterCoordinateThenOutCoordinateError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(4, 7), new Coordinate(3, 8) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertEquals(Error.OUT_COORDINATE, error);
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(4, 3), new Coordinate(3, 4) }, };
        assertEquals(Error.EMPTY_ORIGIN, game.move(coordinates[0][0], coordinates[0][1]));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(3, 6), new Coordinate(2, 7) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertEquals(Error.OPPOSITE_PIECE, error);
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        assertEquals(Error.NOT_DIAGONAL, this.game.move(new Coordinate(5, 2), new Coordinate(4, 2)));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(5, 4), new Coordinate(4, 3) },
                { new Coordinate(1, 6), new Coordinate(2, 7) }, { new Coordinate(4, 3), new Coordinate(3, 4) },
                { new Coordinate(0, 7), new Coordinate(1, 6) }, { new Coordinate(3, 4), new Coordinate(4, 5) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertEquals(Error.NOT_ADVANCED, error);
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(4, 7), new Coordinate(3, 6) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertEquals(Error.NOT_EMPTY_TARGET, error);
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(2, 3);
        target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        Coordinate[][] coordinates = new Coordinate[][] { { new Coordinate(5, 0), new Coordinate(4, 1) },
                { new Coordinate(2, 1), new Coordinate(3, 0) }, { new Coordinate(5, 2), new Coordinate(4, 3) },
                { new Coordinate(3, 0), new Coordinate(5, 2) }, };
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        assertNull(error);
        assertNull(game.getColor(new Coordinate(3, 0)));
        assertNull(game.getColor(new Coordinate(4, 1)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(5, 2)));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        assertEquals(Error.EATING_EMPTY, this.game.move(new Coordinate(5, 4), new Coordinate(3, 2)));
    }

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError() {
        assertEquals(Error.BAD_DISTANCE, this.game.move(new Coordinate(5, 0), new Coordinate(2, 3)));
    }

}