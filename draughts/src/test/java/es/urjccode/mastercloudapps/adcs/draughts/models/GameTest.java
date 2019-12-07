package es.urjccode.mastercloudapps.adcs.draughts.models;

public class GameTest {

    protected Game game;
    protected Game expectedGame;

    protected void setGame(Color color, String... strings) {
        this.game = new GameBuilder().setColor(color).rows(strings).build();
    }

    protected void setExpectedGame(Color color, String... strings) {
        this.expectedGame = new GameBuilder().setColor(color).rows(strings).build();
    }

}
