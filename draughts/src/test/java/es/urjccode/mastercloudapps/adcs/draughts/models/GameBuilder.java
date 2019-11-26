package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class GameBuilder {

    private List<String> strings;

    GameBuilder(){
        this.strings = new ArrayList<String>();
    }

	GameBuilder row(String string) {
        this.strings.add(string);
		return this;
	}

	Game build() {
        Board board = new Board();
        for(int i=0; i<this.strings.size(); i++){
            for(int j=0; j<this.strings.get(i).length(); j++){
                char character = this.strings.get(i).charAt(j);
                if (character =='b'){
                    board.put(new Coordinate(i,j), new Piece(Color.WHITE));
                }

            }
        }
		return new Game(board);
	}


}