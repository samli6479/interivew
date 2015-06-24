package gowatchit.com.matcher;

import gowatchit.com.matcher.dto.MatchedPair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Matcher implements IMatcher{

  public List<MatchedPair> process(String matchFromFile, String moviesDb, String contributorsDb) {
    //Implement your matching algorithm here.
    //Code below is just filler code.

    List<MatchedPair> matchedPairList = new ArrayList<MatchedPair>();
    return matchedPairList;
  }

}
