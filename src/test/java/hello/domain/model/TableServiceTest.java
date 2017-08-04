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
    public void testAddPlayers() throws Exception {
        TableService tableService = new TableService();
        List<Match> matches = new ArrayList<Match>();
        Assert.assertTrue(matches.size() == 0);



        matches.add(new Match("Niklas","Peter","3:1"));
        matches.add(new Match( "Niklas", "Stefan", "1:3"));
        matches.add(new Match( "Rene", "Bernhard", "2:3"));
        tableService.addPlayers(matches);
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

    @Test
    public void testWinLossIncrement(){
        TableService table = new TableService();
        TableService spy = spy(table);


        List<Match> matches = new ArrayList<Match>();
        matches.add(new Match("Niklas","Stefan","3:1"));
        matches.add(new Match( "Niklas", "Stefan", "1:3"));
        matches.add(new Match( "Stefan", "Niklas", "1:3"));


        setupTestWinLossIncrement(matches, spy);




        spy.populateTable();
        Assert.assertThat(table.tables.get(0).getGamesWon(), CoreMatchers.is(2));
        Assert.assertThat(table.tables.get(1).getGamesWon(), CoreMatchers.is(1));

    }

    private void setupTestWinLossIncrement(List<Match> matches,TableService spy){
        List<List<Match>> matchesFiltered1 = generateMatchesFiltered1(matches);
        List<List<Match>> matchesFiltered2 = generateMatchesFiltered2(matches);
        List<String> playerList = generatePlayerList(matches);

        for(int i = 0; i< playerList.size(); i++){
            doReturn(matchesFiltered1.get(i)).when(spy).findByPlayer1(playerList.get(i));
            doReturn(matchesFiltered2.get(i)).when(spy).findByPlayer2(playerList.get(i));
        }


        doReturn(matches).when(spy).findAll();
    }

    private List<List<Match>> generateMatchesFiltered1(List<Match> matches){
        List<String> players = generatePlayerList(matches);
        List<List<Match>> matchesFiltered = new ArrayList<>();
        int x = 0;
        for(String player : players){
            matchesFiltered.add(new ArrayList<Match>());

            for(Match match: matches){
                if(player.equals(match.getPlayer1())){
                    matchesFiltered.get(x).add(match);
                }
            }
            x++;
        }
        return matchesFiltered;
    }

    private List<List<Match>> generateMatchesFiltered2(List<Match> matches){
        List<String> players = generatePlayerList(matches);
        List<List<Match>> matchesFiltered = new ArrayList<>();
        int x = 0;
        for(String player : players){
            matchesFiltered.add(new ArrayList<Match>());

            for(Match match: matches){
                if(player.equals(match.getPlayer2())){
                    matchesFiltered.get(x).add(match);
                }
            }
            x++;
        }
        return matchesFiltered;
    }




    private List<String> generatePlayerList(List<Match> matches){
        List<String> players = new ArrayList<String>();
        for (Match match: matches) {
            String tmp = match.getPlayer1();
            if (!players.contains(tmp)) {
                players.add(tmp);
            }

            tmp = match.getPlayer2();
            if (!players.contains(tmp)) {
                players.add(tmp);
            }
        }
        return players;
    }


}
