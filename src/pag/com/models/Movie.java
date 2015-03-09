package pag.com.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import pag.com.beans.MovieBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

public class Movie {
	
	public static List<MovieBean> getPopularMovies() {
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.MONTH, -3);
	    
	    String dateFormat = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
		
		String movieResponse = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc");
		List<MovieBean> movies = JSONParser.parseMovie(movieResponse);
		String movieResponsePage2 = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc&page=2");
		List<MovieBean> allMovies = JSONParser.parseMovie(movieResponsePage2);
		
		for (MovieBean movie : movies) {
			allMovies.add(movie);
		}
		
		for (MovieBean movie : allMovies) {
			String response = HttpManager.getData("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
			movie.setPlot(JSONParser.parseTitleToPlot(response));
			movie.setGenres(JSONParser.parseMovieGenre(response));
		}
		return allMovies;
	}
	
	public static MovieBean randomMovie() {
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.MONTH, -3);
	    
	    String dateFormat = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
		
		String movieResponse = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc");
		List<MovieBean> movies = JSONParser.parseMovie(movieResponse);
		String movieResponsePage2 = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc&page=2");
		List<MovieBean> allMovies = JSONParser.parseMovie(movieResponsePage2);
		
		for (MovieBean movie : movies) {
			allMovies.add(movie);
		}
		
//		for (MovieBean movie : allMovies) {
//			String response = HttpManager.getData("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
//			movie.setPlot(JSONParser.parseTitleToPlot(response));
//			movie.setGenres(JSONParser.parseMovieGenre(response));
//		}
		
		Random random = new Random();
		
		int randomNumber = random.nextInt(allMovies.size());
		MovieBean movie = allMovies.get(randomNumber);
		String response = HttpManager.getData("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		movie.setPlot(JSONParser.parseTitleToPlot(response));
		movie.setGenres(JSONParser.parseMovieGenre(response));
		
		return movie;
	}
	
	public static List<MovieBean> randomMovies(int number) {
		Calendar now = Calendar.getInstance();
	    now.add(Calendar.MONTH, -3);
	    
	    String dateFormat = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
		
		String movieResponse = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc");
		List<MovieBean> movies = JSONParser.parseMovie(movieResponse);
		String movieResponsePage2 = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b&release_date.lte=" + dateFormat + "&sort_by=popularity.desc&page=2");
		List<MovieBean> allMovies = JSONParser.parseMovie(movieResponsePage2);
		
		for (MovieBean movie : movies) {
			allMovies.add(movie);
		}
		
		for (MovieBean movie : allMovies) {
			String response = HttpManager.getData("http://api.themoviedb.org/3/movie/" + movie.getId() + "?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
			movie.setPlot(JSONParser.parseTitleToPlot(response));
			movie.setGenres(JSONParser.parseMovieGenre(response));
		}
		
		Random random = new Random();
		List<MovieBean> returnMovies = new ArrayList<MovieBean>();
		int randomSizeNumber = allMovies.size();
		
		for (int i = 0; i < number; i++) {
			int randomNumber = random.nextInt(randomSizeNumber);
			MovieBean movie = allMovies.remove(randomNumber);
			returnMovies.add(movie);
			randomSizeNumber--;
		}
		return returnMovies;
	}

}
