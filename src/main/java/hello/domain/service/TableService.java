package hello.domain.service;

import hello.domain.model.Match;
import hello.domain.model.MatchRepository;
import hello.domain.model.PlayerForTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private MatchRepository repository;

    List<PlayerForTable> players = new ArrayList<PlayerForTable>();

    public List<PlayerForTable> populateTable(){
        addPlayers();
        addWinLoss();


        return players;
    }

    public void addWinLoss(){
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


    public int detectWinLoss(String result){
        String[] strings = result.split(":");
        int scorePlayer1 = Integer.valueOf(strings[0]);
        int scorePlayer2 = Integer.valueOf(strings[1]);

        return scorePlayer1 - scorePlayer2;
    }

    public void addPlayers(){
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

    public List<PlayerForTable> getTable() {
        populateTable();
        return players;
    }
}
