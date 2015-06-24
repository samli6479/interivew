require 'spec_helper'

describe 'XboxMatcher' do

  it 'matches xbox movies to gowatchit movies' do
    matcher = XboxMatcher.new
    matched_movies = matcher.process('data/xbox_feed.csv', 'movies.csv', 'actors_and_directors.csv')

    expect(matched_movies).to_not be_empty
  end

end

