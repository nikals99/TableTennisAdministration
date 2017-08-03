package hello.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    List<PlayerForTable> players = new ArrayList<PlayerForTable>();
    MatchRepository repository;
    public List<PlayerForTable> populateTable(MatchRepository repository){
        this.repository = repository;
        addPlayers();
        addWinLos();


        return players;
    }

    private void addWinLos(){
        for(PlayerForTable player : players) {
            for (Match match : repository.findByPlayer1(player.getName())) {
                player.increaseGamesPlayed();
            }
            for (Match match : repository.findByPlayer2(player.getName())) {
                player.increaseGamesPlayed();
            }
        }
    }

    private void addPlayers(){
        for (Match match: repository.findAll()) {
            PlayerForTable tmp = new PlayerForTable(match.getPlayer1());
            if (!playerIsInTable(tmp)) {
                players.add(tmp);
            }

            tmp = new PlayerForTable(match.getPlayer2());
            if (!playerIsInTable(tmp)) {
                players.add(tmp);
            }
        }
    }

    public boolean playerIsInTable(PlayerForTable player){
        for(PlayerForTable x : players){
            if(x.getName() == player.getName()){
                return true;
            }
        }
        return false;
    }


}
