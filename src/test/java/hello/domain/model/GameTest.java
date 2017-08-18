package hello.domain.model;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    @Test
    public void getResult() throws Exception {
        Game game = new Game("Niklas", "Peter","3,-2,3,-5,-5");

        Assert.assertThat(game.getResult(), CoreMatchers.is("2:3"));
    }


    @Test
    public void getResultDetail() throws Exception {
        Game game = new Game("Niklas", "Peter","4,10,-4");

        String[] resultDetail = game.getResultDetail();

        Assert.assertThat(resultDetail.length, CoreMatchers.is(3));
        Assert.assertThat(resultDetail[0], CoreMatchers.is("11:4"));
        Assert.assertThat(resultDetail[1], CoreMatchers.is("12:10"));
        Assert.assertThat(resultDetail[2], CoreMatchers.is("4:11"));
    }



}