package gowatchit;

import gowatchit.com.matcher.IMatcher;
import gowatchit.com.matcher.dto.MatchedPair;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class AppTest extends TestCase
{
    @Autowired
    private IMatcher matcher;

    @org.junit.Test
    public void matchTest()
    {
        List<MatchedPair> matchedMovies = matcher.process("xboxFeed.csv","movies.csv","actors_and_directors.csv");
        for(MatchedPair movie : matchedMovies){
            assertNotSame(-1,movie.getGwiId());
            assertNotSame(null,movie.getXboxId());
        }
        assertTrue("Nothing matched!", matchedMovies.size() > 0);
        System.out.println("Total items matched :"+ matchedMovies.size());
    }
}
