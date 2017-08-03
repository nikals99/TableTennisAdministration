package hello.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    List<PlayerForTable> players = new ArrayList<PlayerForTable>();
    MatchRepository repository;
    public List<PlayerForTable> populateTable(MatchRepository repository){
        this.repository = repository;
        addPlayers();
        addWinLoss();


        return players;
    }

    private void addWinLoss(){
        for(PlayerForTable player : players) {
            for (Match match : repository.findByPlayer1(player.getName())) {
                player.increaseGamesPlayed();
                if(detectWinLoss(match.getResult()) > 0){
                    player.increaseGamesWon();
                }
            }
            for (Match match : repository.findByPlayer2(player.getName())) {
                player.increaseGamesPlayed();
                if(detectWinLoss(match.getResult()) < 0){
                    player.increaseGamesWon();
                }
            }
        }
    }


    private int detectWinLoss(String result){
        String[] strings = result.split(":");
        int scorePlayer1 = Integer.valueOf(strings[0]);
        int scorePlayer2 = Integer.valueOf(strings[1]);

        return scorePlayer1 - scorePlayer2;
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
