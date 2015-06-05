## Welcome

If you're reading this, that means we liked you enough to ask you to another round of interviewing. Congratulations!

Now let's get down to programming...

## The Match Game

GoWatchIt receives feeds from many different providers, from Google Play, VUDU, and Amazon Instant (alongside many others),
that includes information about the availability of a given movie or TV show. These feeds usually include some metadata from
the movie, such as the title, director, and release year, alongside availability information like price and the fulfilment URL.

One of the problems that we have encountered at GoWatchIt is the following: how do we take metadata and availability information found in these
provider feeds and match them to our own internal database of movies? Especially when different feeds have varying types of
metadata and, not to mention, inconsistencies between sources of data.

In this exercise, you will be given a database of movies and a provider feed and you must implement your own matching algorithm
to match availability information to the internal database of movies.


## Getting Started 

You will find some files given to you to start off.

## Data Files

**movies.csv** : a comma-separated file of around 200,000 movies, with the following schema:

| watchable_id | title | year |
| ------------ | ----- | ---- |
| 1            | Finding Nemo | 2006 |

**actors_and_directors.csv**: a comma-separated file of actors and directors associated with each movie. It has the following schema:

| watchable_id | name | role |
| ------------ | ---- | -------- |
| 1            | Leonardo DiCaprio | cast |
| 2            | Martin Scorsese | director|

*Note:* role has only two types: `cast` and `director`

**xbox.csv**: a comma-separated file of the official provider feed for Xbox. It has the following schema:

| MediaId | Title | OriginalReleaseDate | MediaType | Actors | Director | XboxLiveURL |
| ------- | ----- | ------------------- | --------- | ------ | -------- | ----------- |
| 531b964f-0cb9-4968-9b77-e547f2435225| Furious 7 | 4/13/2015 | Movie | Vin Diesel, Paul Walker, Jason Statham | James Wan | video.xbox.com  


## Classes

`/src/main/java/gowatchit/com/matcher/dto/MatchedPair.java`: A Data Transfer Object that consists of a match between a GoWatchIt ID and an Xbox ID.

`/src/main/java/gowatchit/com/matcher/Matcher.java`: Where you will implement your matcher. Must return a List of `MatchedPair` objects

## Compile and Run
This is a maven project, so a simple `mvn clean install` is all you need.
You will see a test is failing. 

#Objective
Make the failing test pass!

