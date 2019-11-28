package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Color {
    WHITE(Color.WHITE_LOWER_LIMIT_ROW),
    BLACK(Color.BLACK_UPPER_LIMIT_ROWS);

    static final int WHITE_LOWER_LIMIT_ROW = 5;
    static final int BLACK_UPPER_LIMIT_ROWS = 2;
    private final int LIMIT;

    private Color(int limit){
        this.LIMIT = limit;
    }

    boolean isInitialRow(final int row){
        switch(this){
            case WHITE:
                return row >= LIMIT;
            case BLACK:
                return row <= LIMIT;
        }
        return false;
    }

    static Color getInitialColor(final int row) {
        for(Color color : Color.values()){
            if (color.isInitialRow(row)){
                return color;
            }
        }
        return null;
    }
	
}