package hello.domain.model;


import hello.domain.service.TableService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TableServiceTest {
    @Test
    public void addPlayers() throws Exception {
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
    public void populateTable() throws Exception {

    }

    @Test
    public void detectWinLos(){
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
}
