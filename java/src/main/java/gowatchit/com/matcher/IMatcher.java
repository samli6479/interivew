package gowatchit.com.matcher;

import gowatchit.com.matcher.dto.MatchedPair;

import java.util.List;

public interface IMatcher {

  List<MatchedPair> process(String matchFromFile, String moviesDb, String contributorsDb);

}
