package hello.domain.model;


import hello.domain.service.TableService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class TableTest {


    @Test
    public void populateTable() throws Exception {

    }

    @Test
    public void testPlayerIsInTable() throws Exception {
        TableService table = new TableService();
        boolean tmp = table.playerIsInTable(new PlayerForTable("Stefan"));

        Assert.assertThat(tmp,CoreMatchers.is(false));

        table.players.add(new PlayerForTable("Stefan"));
        tmp = table.playerIsInTable(new PlayerForTable("Stefan"));

        Assert.assertThat(tmp,CoreMatchers.is(true));

    }


}
