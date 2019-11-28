package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class GameBuilder {

    private List<String> strings;

    GameBuilder(){
        this.strings = new ArrayList<String>();
    }

    GameBuilder rows(String ... strings){
        for(String string : strings){
            assert Pattern.matches("[bn ]{8}", string);
            this.strings.add(string);
        }
        return this;
    }

	Game build() {
        Board board = new Board();
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

}