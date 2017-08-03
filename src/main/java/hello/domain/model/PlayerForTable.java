package hello.domain.model;

public class PlayerForTable {
    String name;
    int gamesPlayed;


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
}
