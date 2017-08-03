package hello.domain.model;

public class PlayerForTable {
    private String name;
    private int gamesPlayed;
    private int gamesWon;


    public PlayerForTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void increaseGamesPlayed(){
        gamesPlayed++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void increaseGamesWon(){
        gamesWon++;
    }
}
