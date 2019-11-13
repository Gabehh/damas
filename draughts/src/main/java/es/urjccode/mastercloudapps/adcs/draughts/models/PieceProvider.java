package es.urjccode.mastercloudapps.adcs.draughts.models;

interface PieceProvider {

    boolean isEmpty(Coordinate coordinate);
    Piece getPiece(Coordinate coordinate);
}