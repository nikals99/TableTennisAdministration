package hello.domain.model;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchTest {
    @Test
    public void getResult() throws Exception {
        Match match = new Match("Niklas", "Peter","3,-2,3,-5,-5");

        Assert.assertThat(match.getResult(), CoreMatchers.is("2:3"));
    }


    @Test
    public void getResultDetail() throws Exception {
        Match match = new Match("Niklas", "Peter","4,10,-4");

        String[] resultDetail = match.getResultDetail();

        Assert.assertThat(resultDetail.length, CoreMatchers.is(3));
        Assert.assertThat(resultDetail[0], CoreMatchers.is("11:4"));
        Assert.assertThat(resultDetail[1], CoreMatchers.is("12:10"));
        Assert.assertThat(resultDetail[2], CoreMatchers.is("4:11"));
    }



}