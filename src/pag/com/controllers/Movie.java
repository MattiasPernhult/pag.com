package pag.com.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import pag.com.beans.MovieBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.ApiKeys;

public class Movie {

	public static MovieBean randomMovie(int genre) {
		String movieResponse = HttpManager
				.getData("http://api.themoviedb.org/3/genre/" + genre
						+ "/movies?api_key=" + ApiKeys.getTMDBKey());
		List<MovieBean> movies = JSONParser.parseMovie(movieResponse);
		String movieResponsePage2 = HttpManager
				.getData("http://api.themoviedb.org/3/genre/" + genre
						+ "/movies?api_key=" + ApiKeys.getTMDBKey() + "&page=2");
		List<MovieBean> allMovies = JSONParser.parseMovie(movieResponsePage2);

		if (movies == null && allMovies == null) {
			return null;
		} else if (movies != null && allMovies == null) {
			return getRandomMovie(movies);
		} else if (movies == null && allMovies != null) {
			return getRandomMovie(allMovies);
		} else {
			for (MovieBean movie : movies) {
				allMovies.add(movie);
			}
			return getRandomMovie(allMovies);
		}
	}
	
	public static MovieBean randomMovie() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, -2);

		String dateFormat = now.get(Calendar.YEAR) + "-"
				+ (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);

		String movieResponse = HttpManager
				.getData("http://api.themoviedb.org/3/discover/movie?api_key="
						+ ApiKeys.getTMDBKey() + "&release_date.lte="
						+ dateFormat + "&");
		List<MovieBean> movies = JSONParser.parseMovie(movieResponse);
		String movieResponsePage2 = HttpManager
				.getData("http://api.themoviedb.org/3/discover/movie?api_key="
						+ ApiKeys.getTMDBKey() + "&release_date.lte="
						+ dateFormat + "&page=2");
		List<MovieBean> allMovies = JSONParser.parseMovie(movieResponsePage2);

		if (movies == null && allMovies == null) {
			return null;
		} else if (movies != null && allMovies == null) {
			return getRandomMovie(movies);
		} else if (movies == null && allMovies != null) {
			return getRandomMovie(allMovies);
		} else {
			for (MovieBean movie : movies) {
				allMovies.add(movie);
			}
			return getRandomMovie(allMovies);
		}
	}

	private static MovieBean getRandomMovie(List<MovieBean> movies) {
		Random random = new Random();
		int randomNumber = random.nextInt(movies.size());
		MovieBean movie = movies.get(randomNumber);
		String response = HttpManager
				.getData("http://api.themoviedb.org/3/movie/" + movie.getId()
						+ "?api_key=" + ApiKeys.getTMDBKey());
		movie.setPlot(JSONParser.parseTitleToPlot(response));
		movie.setGenres(JSONParser.parseMovieGenre(response));

		return movie;
	}
}
