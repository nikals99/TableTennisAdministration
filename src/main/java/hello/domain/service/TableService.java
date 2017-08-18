package hello.domain.service;

import hello.domain.model.Game;
import hello.domain.model.GameRepository;
import hello.domain.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService {

    @Autowired
    private GameRepository repository;

    public List<Table> tables = new ArrayList<Table>();
    List<Game> games = new ArrayList<Game>();

    public List<Table> populateTable(){
        games = findAll();
        addPlayers(games);
        addWinLoss();
        calculateElo();

        return tables;
    }

    public void addWinLoss(){
        for(Table player : tables) {
            player.resetPlayer();
            for (Game game : findByPlayer1(player.getName())) {
                player.increaseGamesPlayed();
                if(detectWinLoss(game.getResult()) > 0){
                    player.increaseGamesWon();
                }
            }
            for (Game game : findByPlayer2(player.getName())) {
                player.increaseGamesPlayed();
                if(detectWinLoss(game.getResult()) < 0){
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

    public void addPlayers(List<Game> games){
        for (Game game : games) {
            Table tmp = new Table(game.getPlayer1());
            if (!playerIsInTable(tmp)) {
                tables.add(tmp);
            }

            tmp = new Table(game.getPlayer2());
            if (!playerIsInTable(tmp)) {
                tables.add(tmp);
            }
        }
    }

    public boolean playerIsInTable(Table player){
        for(Table x : tables){
            if(x.getName().equals(player.getName())){
                return true;
            }
        }
        return false;
    }

    public List<Table> getTable() {
        populateTable();
        tables.sort(Comparator.comparing(Table::getElo));
        Collections.reverse(tables);
        return tables;
    }

    public List<Game> findByPlayer1(String name){
        return repository.findByPlayer1(name);
    }

    public List<Game> findByPlayer2(String name){
        return repository.findByPlayer2(name);
    }

    public List<Game> findAll(){
        List<Game> games = new ArrayList<Game>();
        for (Game game : repository.findAll()) {
            games.add(game);
        }
        return games;
    }

    public void calculateElo(){
        for(Game game: games){
            int eloPlayer1 = 0,eloPlayer2= 0;
            for(Table player: tables){
                if(player.getName().equals(game.getPlayer1())){
                    eloPlayer1 = player.getElo();
                }else if(player.getName().equals(game.getPlayer2())){
                    eloPlayer2 = player.getElo();
                }
            }
            int eloDifference = calulateEloDifference(eloPlayer1,eloPlayer2,calculateWinner(game.getResult()));
            for(Table player: tables){
                if(player.getName().equals(game.getPlayer1())){
                   player.setElo(eloPlayer1+eloDifference);
                }else if(player.getName().equals(game.getPlayer2())){
                    player.setElo(eloPlayer2-eloDifference);
                }
            }
        }
    }

    public int calculateWinner(String res){
       if(detectWinLoss(res)>0){
        return 1;
       }else {
           return 0;
       }
    }

    public int calulateEloDifference(double eloPlayer1, double eloPlayer2, int result){
        int eloDifference;
        int k = 32;
        double expectedResult;


        expectedResult = 1.0 / (1.0 + Math.pow(10.0, (eloPlayer2 - eloPlayer1) / 400.0));
        eloDifference = (int)Math.round(k*(result-expectedResult));


        return eloDifference;
    }
}
