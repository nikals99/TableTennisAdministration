package hello.domain.model;


import hello.domain.service.TableService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

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


        List<Match> matchesFiltered = new ArrayList<Match>();
        matchesFiltered.add(new Match("Niklas","Stefan","3:1"));
        matchesFiltered.add(new Match( "Niklas", "Stefan", "1:3"));

        List<Match> matchesEmpty = new ArrayList<Match>();

        doReturn(matchesFiltered).when(spy).findByPlayer1("Niklas");
        doReturn(matchesFiltered).when(spy).findByPlayer1("Stefan");
        doReturn(matches).when(spy).findAll();
        doReturn(matchesEmpty).when(spy).findByPlayer2("Stefan");
        doReturn(matchesEmpty).when(spy).findByPlayer2("Niklas");

        spy.populateTable();
        Assert.assertThat(table.tables.get(0).getGamesWon(), CoreMatchers.is(1));


    }
}
