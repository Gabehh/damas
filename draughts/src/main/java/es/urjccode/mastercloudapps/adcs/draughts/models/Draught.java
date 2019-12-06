package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

  static char[] CHARACTERS = { 'B', 'N' };

  Draught(Color color) {
    super(color);
  }

  @Override
  Error isCorrectMovement(Piece between, int pair, Coordinate... coordinates) {
    assert coordinates[pair] != null;
    assert coordinates[pair + 1] != null;
    return null;
  }

  @Override
  protected char[] getCodes() {
    return Draught.CHARACTERS;
  }

}