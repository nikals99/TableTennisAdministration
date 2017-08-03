package hello.domain.service;

import hello.domain.model.Match;
import hello.domain.model.MatchRepository;
import hello.domain.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private MatchRepository repository;

    public List<Table> tables = new ArrayList<Table>();
    List<Match> matches = new ArrayList<Match>();

    public List<Table> populateTable(){
        for (Match match: repository.findAll()) {
            matches.add(match);
        }

        addPlayers(matches);
        addWinLoss();


        return tables;
    }

    public void addWinLoss(){
        for(Table player : tables) {
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

    public void addPlayers(List<Match> matches){
        for (Match match: matches) {
            Table tmp = new Table(match.getPlayer1());
            if (!playerIsInTable(tmp)) {
                tables.add(tmp);
            }

            tmp = new Table(match.getPlayer2());
            if (!playerIsInTable(tmp)) {
                tables.add(tmp);
            }
        }
    }

    public boolean playerIsInTable(Table player){
        for(Table x : tables){
            if(x.getName() == player.getName()){
                return true;
            }
        }
        return false;
    }

    public List<Table> getTable() {
        populateTable();
        return tables;
    }
}
