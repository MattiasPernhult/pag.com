package pag.com.apis;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import pag.com.beans.ErrorBean;
import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;
import pag.com.builders.CustomJSONBuilder;
import pag.com.controllers.Movie;
import pag.com.util.Helper;

@Path("/tips")
public class TipApi {

	Gson gson = new Gson();

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movie")
	public Response getMovieTip(@Context Request request) {
		MovieBean movie = Helper.getRandomMovie();

		if (movie != null) {
			Helper.addLinkToMovie(movie);
			String response = CustomJSONBuilder.buildMovies(movie);
			return Response.ok(response).build();
		}
		ErrorBean error = new ErrorBean();
		error.setMessage("Communication failure with The Movie Database API.");
		return Response.serverError().entity(gson.toJson(error)).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movie/snack")
	public Response getMovieSnacksTip(@QueryParam("kind") int kindId,
			@Context Request request) {
		MovieBean movie = Helper.getRandomMovie();

		if (movie != null) {
			Helper.addLinkToMovie(movie);
			List<SnacksBean> snacks = Helper.findSnacksForMovie(
					movie.getGenres(), kindId);
			if (snacks == null) {
				ErrorBean error = new ErrorBean();
				error.setMessage("Communication failure with Snacks API. Please contact Milan Gajic at supergajic@gmail.com.");
				return Response.serverError().entity(gson.toJson(error))
						.build();
			}
			SnacksBean snack = Helper.getRandomSnacksFromList(snacks);
			String response = CustomJSONBuilder.buildMovieSnack(movie, snack);
			return Response.ok(response).build();
		}
		ErrorBean error = new ErrorBean();
		error.setMessage("Communication failure with The Movie Database API.");
		return Response.serverError().entity(gson.toJson(error)).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movie/drink")
	public Response getMovieSnacks(@QueryParam("drink") int drinkId,
			@Context Request request) {
		MovieBean movie = Helper.getRandomMovie();

		if (movie != null) {
			Helper.addLinkToMovie(movie);
			Object object = Helper
					.getDrinksForMovie(movie.getGenres(), drinkId);
			if (object == null) {
				ErrorBean error = new ErrorBean();
				error.setMessage("Communication failure with Snooth API.");
				return Response.serverError().entity(gson.toJson(error))
						.build();
			}
			String response = CustomJSONBuilder.buildMovieDrink(movie, object);
			return Response.ok(response).build();
		}
		ErrorBean error = new ErrorBean();
		error.setMessage("Communication failure with The Movie Database API.");
		return Response.serverError().entity(gson.toJson(error)).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/movie/snack/drink")
	public Response getMovieSnacksDrinks(@QueryParam("kind") int kindId,
			@QueryParam("drink") int drinkId, @Context Request request) {
		MovieBean movie = Helper.getRandomMovie();

		if (movie != null) {
			Helper.addLinkToMovie(movie);
			Object object = Helper
					.getDrinksForMovie(movie.getGenres(), drinkId);
			if (object == null) {
				ErrorBean error = new ErrorBean();
				error.setMessage("Communication failure with Snooth API.");
				return Response.serverError().entity(gson.toJson(error))
						.build();
			}
			List<SnacksBean> snacks = Helper.findSnacksForMovie(
					movie.getGenres(), kindId);
			if (snacks == null) {
				ErrorBean error = new ErrorBean();
				error.setMessage("Communication failure with Snacks API. Please contact Milan Gajic at supergajic@gmail.com.");
				return Response.serverError().entity(gson.toJson(error))
						.build();
			}
			SnacksBean snack = Helper.getRandomSnacksFromList(snacks);
			String response = CustomJSONBuilder.buildMovieSnackDrink(movie,
					snack, object);
			return Response.ok(response).build();
		}
		ErrorBean error = new ErrorBean();
		error.setMessage("Communication failure with The Movie Database API.");
		return Response.serverError().entity(gson.toJson(error)).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/snack")
	public Response getSnacks(@QueryParam("movie_genre") String genre,
			@QueryParam("kind") int kindId, @Context Request request) {
		if (genre == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("The url are missing som parts. Add movie genre to complete it.");
			return Response.status(Status.BAD_REQUEST)
					.entity(gson.toJson(error)).build();
		}

		String[] genresArray = genre.split(" ");
		List<String> genres = Arrays.asList(genresArray);

		List<SnacksBean> snacks = Helper.findSnacksForMovie(genres, kindId);
		if (snacks == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("Communication failure with Snacks API. Please contact Milan Gajic at supergajic@gmail.com.");
			return Response.serverError().entity(gson.toJson(error)).build();
		}
		SnacksBean snack = Helper.getRandomSnacksFromList(snacks);
		String response = CustomJSONBuilder.buildSnack(snack);
		return Response.ok(response).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("snack/drink")
	public Response getSnacksDrinks(@QueryParam("movie_genre") String genre,
			@QueryParam("kind") int kindId, @QueryParam("drink") int drinkId,
			@Context Request request) {
		if (genre == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("The url are missing som parts. Add movie genre to complete it.");
			return Response.status(Status.BAD_REQUEST)
					.entity(gson.toJson(error)).build();
		}
		String[] genresArray = genre.split(" ");
		List<String> genres = Arrays.asList(genresArray);

		List<SnacksBean> snacks = Helper.findSnacksForMovie(genres, kindId);
		if (snacks == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("Communication failure with Snacks API. Please contact Milan Gajic at supergajic@gmail.com.");
			return Response.serverError().entity(gson.toJson(error)).build();
		}
		SnacksBean snack = Helper.getRandomSnacksFromList(snacks);

		Object drink = Helper.getDrinksForMovie(genres, drinkId);
		if (drink == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("Communication failure with Snooth API.");
			return Response.serverError().entity(gson.toJson(error)).build();
		}
		String response = CustomJSONBuilder.buildSnackDrink(snack, drink);
		return Response.ok(response).build();
	}

	// KLAR
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/drink")
	public Response getDrinks(@QueryParam("movie_genre") String genre,
			@QueryParam("drink") int drinkId, @Context Request request) {
		if (genre == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("The url are missing som parts. Add movie genre to complete it.");
			return Response.status(Status.BAD_REQUEST)
					.entity(gson.toJson(error)).build();
		}
		String[] genresArray = genre.split(" ");
		List<String> genres = Arrays.asList(genresArray);

		Object drink = Helper.getDrinksForMovie(genres, drinkId);
		if (drink == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("Communication failure with Snooth API.");
			return Response.serverError().entity(gson.toJson(error)).build();
		}
		String response = CustomJSONBuilder.buildDrinks(drink);
		return Response.ok(response).build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/snack/kinds")
	public Response getSnackKind() {
		String response = Helper.getSnackKind();
		if (response == null) {
			ErrorBean error = new ErrorBean();
			error.setMessage("Communication failure with Snacks API. Please contact Milan Gajic at supergajic@gmail.com.");
			return Response.serverError().entity(gson.toJson(error)).build();
		}
		return Response.ok(response).build();
	}
}
