package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

class Draught extends Piece {

  private static char[] CHARACTERS = { 'B', 'N' };

  Draught(Color color) {
    super(color);
  }

  @Override
  Error isCorrectDiagonalMovement(List<Piece> betweenDiagonalPieces, int pair, Coordinate... coordinates) {
    if (betweenDiagonalPieces.size() > 1)
      return Error.TOO_MUCH_EATINGS;
    return null;
  }

  @Override
  protected char[] getCodes() {
    return Draught.CHARACTERS;
  }

}