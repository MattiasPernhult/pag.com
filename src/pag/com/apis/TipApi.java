package pag.com.apis;

import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;
import pag.com.beans.WineBean;
import pag.com.builders.CustomJSONBuilder;
import pag.com.builders.CustomResponseBuilder;
import pag.com.connections.HttpManager;
import pag.com.models.Movie;
import pag.com.parsers.JSONParser;
import pag.com.util.Helper;

@Path("/tips")
public class TipApi {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movies")
	public Response getMovieTip(@Context Request request) {
		MovieBean movies = Movie.randomMovie();
		String response = CustomJSONBuilder.buildMovies(movies);
		return Response.ok(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movies/snacks")
	public Response getMovieSnacksTip(@Context Request request) {
		MovieBean movie = Movie.randomMovie();
		List<SnacksBean> snacks = Helper.findSnacksForMovie(movie.getGenres());
		SnacksBean snack = Helper.getRandomSnacksFromList(snacks);
		String response = CustomJSONBuilder.buildMovieSnack(movie, snack);
		return Response.ok(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
	@Path("/movies/drinks")
	public Response getMovieSnacks(@Context Request request) {
		MovieBean movie = Movie.randomMovie();
		Object object = Helper.getDrinksForMovie(movie.getGenres());
		String response = CustomJSONBuilder.buildMovieDrink(movie, object);
		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
	@Path("/movies/snacks/drinks")
	public Response getMovieSnacksDrinks(@Context Request request) {
		MovieBean movie = Movie.randomMovie();
		Object object = Helper.getDrinksForMovie(movie.getGenres());
		List<SnacksBean> snacks = Helper.findSnacksForMovie(movie.getGenres());
		if (snacks == null) 
			System.out.println("null när listan hämtades");
		SnacksBean snack = Helper.getRandomSnacksFromList(snacks);
		String response = CustomJSONBuilder.buildMovieSnackDrink(movie, snack, object);
		return Response.ok(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
	@Path("/snacks")
	public Response getSnacks(@Context Request request) {
		SnacksBean snack = Helper.getRandomSnack();
		String response = CustomJSONBuilder.buildSnack(snack);
		return Response.ok(response).build();
	}
	
	
}
