package hello.domain.model;


public class Table {
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int elo;

    public void resetPlayer(){
        gamesPlayed = 0;
        gamesWon =0;
        elo = 1500;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public Table(String name) {
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

    public int getWinLosDiff(){
        return gamesWon- (gamesPlayed - gamesWon);
    }



}
