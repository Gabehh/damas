package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Error {
    BAD_FORMAT,
    EMPTY_ORIGIN, 
    OPPOSITE_PIECE, 
    NOT_DIAGONAL, 
    BAD_DISTANCE,
    NOT_EMPTY_TARGET, 
    NOT_ADVANCED, 
    EATING_EMPTY;
}