package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class GameBuilder {

    private List<String> strings;
    private Board board;

    GameBuilder(){
        this.strings = new ArrayList<String>();
        this.board = new Board();
    }

	GameBuilder row(String string) {
        assert string.length() == this.board.getDimension();
        assert Pattern.matches("[bn ]{8}", string);
        this.strings.add(string);
		return this;
    }

	Game build() {
        assert this.strings.size() == board.getDimension();
        for(int i=0; i<this.strings.size(); i++){
            for(int j=0; j<this.strings.get(i).length(); j++){
                char character = this.strings.get(i).charAt(j);
                Color color = null;
                if (character =='b'){
                    color = Color.WHITE;
                } else if (character == 'n'){
                    color = Color.BLACK;
                }
                if (color != null){
                    board.put(new Coordinate(i,j), new Piece(color));
                }               
            }
        }
		return new Game(board);
    }
    
    static Game rows(String ... strings){
        GameBuilder gameBuilder = new GameBuilder();
        for(String string : strings){
            gameBuilder.row(string);
        }
        return gameBuilder.build();
    }


}