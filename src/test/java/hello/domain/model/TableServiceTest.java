package hello.domain.model;


import hello.domain.service.TableService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TableServiceTest {
    @Test
    public void calulateSingleElo() throws Exception {
        TableService tableService = new TableService();

        Assert.assertThat(tableService.calulateEloDifference(1500,1500,1),CoreMatchers.is(16));
        Assert.assertThat(tableService.calulateEloDifference(1000,1000,1),CoreMatchers.is(16));
        Assert.assertThat(tableService.calulateEloDifference(2000,2000,0),CoreMatchers.is(-16));

        Assert.assertThat(tableService.calulateEloDifference(1600,1400,1),CoreMatchers.is(8));
        Assert.assertThat(tableService.calulateEloDifference(1400,1600,1),CoreMatchers.is(24));

        Assert.assertThat(tableService.calulateEloDifference(1800,1200,1),CoreMatchers.is(1));
        Assert.assertThat(tableService.calulateEloDifference(1800,1200,1),CoreMatchers.is(1));

        Assert.assertThat(tableService.calulateEloDifference(1000,1000,1),CoreMatchers.is(16));
        Assert.assertThat(tableService.calulateEloDifference(1000,1000,1),CoreMatchers.is(16));

    }

    @Test
    public void testAddPlayers() throws Exception {
        TableService tableService = new TableService();
        List<Game> games = new ArrayList<Game>();



        games.add(new Game("Niklas","Peter","3:1"));
        tableService.addPlayers(games);
        Assert.assertThat( tableService.tables.size(), CoreMatchers.is(2));
        games.add(new Game( "Niklas", "Stefan", "1:3"));
        games.add(new Game( "Rene", "Bernhard", "2:3"));
        tableService.addPlayers(games);
        Assert.assertThat( tableService.tables.size(), CoreMatchers.is(5));
    }


    @Test
    public void testAddpopulateTable() throws Exception {

    }

    @Test
    public void testDetectWinLos(){
        TableService tableService = new TableService();

        Assert.assertTrue( 0 < tableService.detectWinLoss("3:1"));
        Assert.assertTrue( 0 > tableService.detectWinLoss("1:3"));
        Assert.assertTrue( 0 == tableService.detectWinLoss("1:1"));


    }

    @Test
    public void testPlayerIsInTable() throws Exception {
        TableService table = new TableService();
        boolean tmp = table.playerIsInTable(new Table("Stefan"));

        Assert.assertThat(tmp,CoreMatchers.is(false));

        table.tables.add(new Table("Stefan"));
        tmp = table.playerIsInTable(new Table("Stefan"));

        Assert.assertThat(tmp,CoreMatchers.is(true));

    }

   // @Test
    public void testWinLossIncrement(){
        TableService table = new TableService();
        TableService spy = spy(table);


        List<Game> games = new ArrayList<Game>();
        games.add(new Game("Niklas","Stefan","3:1"));
        games.add(new Game( "Niklas", "Stefan", "1:3"));
        games.add(new Game( "Stefan", "Niklas", "1:3"));


        setupTestWinLossIncrement(games, spy);




        spy.populateTable();
        Assert.assertThat(table.tables.get(0).getGamesWon(), CoreMatchers.is(2));
        Assert.assertThat(table.tables.get(1).getGamesWon(), CoreMatchers.is(1));

    }

    private void setupTestWinLossIncrement(List<Game> games, TableService spy){
        List<List<Game>> matchesFiltered1 = generateMatchesFiltered1(games);
        List<List<Game>> matchesFiltered2 = generateMatchesFiltered2(games);
        List<String> playerList = generatePlayerList(games);

        for(int i = 0; i< playerList.size(); i++){
            doReturn(matchesFiltered1.get(i)).when(spy).findByPlayer1(playerList.get(i));
            doReturn(matchesFiltered2.get(i)).when(spy).findByPlayer2(playerList.get(i));
        }


        doReturn(games).when(spy).findAll();
    }

    private List<List<Game>> generateMatchesFiltered1(List<Game> games){
        List<String> players = generatePlayerList(games);
        List<List<Game>> matchesFiltered = new ArrayList<>();
        int x = 0;
        for(String player : players){
            matchesFiltered.add(new ArrayList<Game>());

            for(Game game : games){
                if(player.equals(game.getPlayer1())){
                    matchesFiltered.get(x).add(game);
                }
            }
            x++;
        }
        return matchesFiltered;
    }

    private List<List<Game>> generateMatchesFiltered2(List<Game> games){
        List<String> players = generatePlayerList(games);
        List<List<Game>> matchesFiltered = new ArrayList<>();
        int x = 0;
        for(String player : players){
            matchesFiltered.add(new ArrayList<Game>());

            for(Game game : games){
                if(player.equals(game.getPlayer2())){
                    matchesFiltered.get(x).add(game);
                }
            }
            x++;
        }
        return matchesFiltered;
    }




    private List<String> generatePlayerList(List<Game> games){
        List<String> players = new ArrayList<String>();
        for (Game game : games) {
            String tmp = game.getPlayer1();
            if (!players.contains(tmp)) {
                players.add(tmp);
            }

            tmp = game.getPlayer2();
            if (!players.contains(tmp)) {
                players.add(tmp);
            }
        }
        return players;
    }


}
