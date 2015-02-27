package pag.com.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import pag.com.beans.MovieBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.Helper;

@Path("/movies")
public class Movie {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovies(@QueryParam("g") int genre) {
		String response;
		if (genre == 0)
			response = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		else 
			response = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&with_genres=" + genre + "&api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		List<MovieBean> movies = JSONParser.parseMovie(response);
		
		for (MovieBean movie : movies) {
			String newStr = Helper.replaceSpaces(movie.getTitle());
			String response2 = HttpManager.getData("http://www.omdbapi.com/?t=" + newStr);
			movie.setPlot(JSONParser.parseTitleToPlot(response2));
			movie.setRating(JSONParser.parseTitleToRating(response2));
		}
		Gson gson = new Gson();
		
		return Response.ok(gson.toJson(movies)).build();
	}
	
	@GET 
	@Path("/genre")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getMovieGenre() {
		String response = HttpManager.getData("http://api.themoviedb.org/3/genre/movie/list?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		return null;
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		String response = HttpManager.getData("http://api.themoviedb.org/3/genre/movie/list?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		Gson gson = new Gson();
		return Response.ok(gson.toJson(response)).build();
	}
	
	@GET
	@Path("/toplist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToplist() {
		String response = HttpManager.getData("http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&vote_count.gte=1000&api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		
		List<MovieBean> movies = JSONParser.parseMovieToplist(response);
		
		loop(movies);
		
		Gson gson = new Gson();
		
		return Response.ok(gson.toJson(movies)).build();
	}
	
	private void loop(List<MovieBean> movies) {
		for (MovieBean movie : movies) {
			String newStr = Helper.replaceSpaces(movie.getTitle());
			String response2 = HttpManager.getData("http://www.omdbapi.com/?t=" + newStr);
			movie.setPlot(JSONParser.parseTitleToPlot(response2));
		}
	}
	
//	@GET 
//	@Produces(MediaType.APPLICATION_JSON) 
//	public String get(@Context UriInfo uri) {
//		List<String> queries = uri.getQueryParameters().get("g");
//		String response = "";
//		int i = 0;
//		for (String s : queries) {
//			response += s;
//			i++;
//		}
//		return response + " " + i;
//	}
}
