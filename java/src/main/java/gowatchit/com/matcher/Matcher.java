package gowatchit.com.matcher;

import gowatchit.com.matcher.dto.MatchedPair;
import gowatchit.com.matcher.dto.Movie;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class Matcher implements IMatcher{

    final static Logger logger = LogManager.getLogger();

    public List<MatchedPair> process(String matchFromFile, String moviesDb, String contributorsDb) {
    //Implement your matching algorithm here.
    //Code below is just filler code.

      List<Movie> xboxData = parse(matchFromFile);

      List<Movie>  gowatchitData= merger(moviesDb, contributorsDb);

    List<MatchedPair> matchedPairList = match(xboxData, gowatchitData);
    return matchedPairList;
  }

  public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( NumberFormatException e ) {
            return false;

        }
    }

  public boolean match(String titleOne, String titleTwo, Integer ratio){
      return FuzzySearch.partialRatio(titleOne,titleTwo) > ratio;
  }

  public List<MatchedPair> match (List<Movie> xboxData, List<Movie> gowatchitData) {

      List<MatchedPair> res = new ArrayList<>();

      for (Movie xbox : xboxData) {
          for (Movie gowatchit : gowatchitData) {
              String xboxTitle = xbox.getTitle();
              String gowatchitTitle = gowatchit.getTitle();

              if (match(xboxTitle, gowatchitTitle, 80) || match(gowatchitTitle, xboxTitle, 80)) {
                  String xboxDire = xbox.getDirector();
                  String gowatchitDire = gowatchit.getDirector();

                  String xboxYear = xbox.getYear();
                  String gowatchitYear = gowatchit.getYear();

                  List<String> gowatchitCasts = gowatchit.getCasts();
                  List<String> xboxCasts = xbox.getCasts();


                  if (StringUtils.equalsIgnoreCase(xboxDire, gowatchitDire)) {
                      MatchedPair matchedPair = new MatchedPair(gowatchit.getGwiId(), xbox.getXboxId());
                      res.add(matchedPair);
                  } else if (StringUtils.equalsIgnoreCase(xboxYear, gowatchitYear)) {
                      MatchedPair matchedPair = new MatchedPair(gowatchit.getGwiId(), xbox.getXboxId());
                      res.add(matchedPair);
                  } else if (CollectionUtils.isEqualCollection(gowatchitCasts, xboxCasts)) {
                      MatchedPair matchedPair = new MatchedPair(gowatchit.getGwiId(), xbox.getXboxId());
                      res.add(matchedPair);
                  }

              }

          }
      }

      return res;

  }

  public List<Movie> parse(String xbox){

      List<Movie> movieList = new ArrayList<>();

      ClassPathResource xboxResource = new ClassPathResource(xbox);

      try {


          InputStream inputStream = xboxResource.getInputStream();

          InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

          BufferedReader br = new BufferedReader(inputStreamReader);

          br.readLine();

          for (String line; (line = br.readLine()) != null; ) {
              String[] info = line.split(",(?=([^\"]|\"[^\"]*\")*$)");

              try {


                  String xboxId = info[2];

                  String title = info[3];

                  String[] casts = info[15].split(",");

                  String director = info[16];

                  Movie movie = new Movie();

                  movie.setTitle(title);

                  movie.setCasts(Arrays.asList(casts));

                  movie.setYear(info[4].split(" ")[0].split("/")[2]);

                  movie.setDirector(director);

                  movie.setXboxId(xboxId);

                  movieList.add(movie);

              }catch (NullPointerException ex){
                  String error = new String("Data is null " + info);

                  logger.error(error);
              }
          }
      }catch (IOException ex){
          logger.error(ex.getMessage());
      }

      return movieList;
  }

  public List<Movie> merger(String movies, String actors) {

    Map<Integer, Movie> movieMap = new HashMap<>();

    ClassPathResource movieResource = new ClassPathResource(movies);

    ClassPathResource actorResource = new ClassPathResource(actors);

    try {

        InputStream inputStream = movieResource.getInputStream();

          InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

          BufferedReader br = new BufferedReader(inputStreamReader);

          br.readLine();



          for (String line; (line = br.readLine())!= null;){

              String[] info = line.split(",(?=([^\"]|\"[^\"]*\")*$)");

              Movie movie = new Movie();

              try {
                  if(isInteger(info[0])) {

                      movie.setGwiId(Integer.parseInt(info[0]));

                      movie.setTitle(info[1]);

                      movie.setYear(info[2]);

                      movie.setCasts(new ArrayList<>());

                      movieMap.put(movie.getGwiId(), movie);
                  }
                  else {
                      String error = new String("Data Parsing error " + info.toString());

                      logger.error(error);
                  }
              }catch (NullPointerException ex){

                  String error = new String("Data is null " + info);

                    logger.error(error);
              }


          }

      } catch (IOException ex) {
        logger.error(ex.getMessage());

    }

      try {InputStream inputStream = actorResource.getInputStream();

          InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

          BufferedReader br = new BufferedReader(inputStreamReader);

          br.readLine();

          for (String line; (line = br.readLine())!= null;){

              String[] info = line.split(",(?=([^\"]|\"[^\"]*\")*$)");

              try {
                  if(isInteger(info[0])) {

                      Movie movie = movieMap.get(info[0]);

                      if(movie != null) {

                          if (StringUtils.equalsIgnoreCase(info[3], "cast")) {
                              List<String> actorList = movie.getCasts();
                              actorList.add(info[3]);
                              movie.setCasts(actorList);
                          }
                          if (StringUtils.equalsIgnoreCase(info[3], "director")) {
                              movie.setDirector(info[3]);
                          }
                      }
                  }
              }catch (NullPointerException ex){
                  String error = new String("Data is null " + info);
                  logger.error(error);
              }
          }

      } catch (IOException ex) {
            logger.error(ex.getMessage());
      }

    List<Movie> res = new ArrayList<>(movieMap.values());

    return res;
  }

}
