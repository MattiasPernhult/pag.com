package pag.com.builders;

import java.util.List;

import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CustomJSONBuilder {

	private static Gson gson = new Gson();
	
	public static String buildMovies(MovieBean movie) {
		JsonElement movieElement = gson.toJsonTree(movie);
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("movie", movieElement);
		return jsonObject.toString();
	}
	
	public static String buildSnackDrink(SnacksBean snack, Object object) {
		JsonElement snackElement = gson.toJsonTree(snack);
		JsonElement drinkElement = gson.toJsonTree(object);
		JsonObject json = new JsonObject();
		json.add("snack", snackElement);
		json.add("drink", drinkElement);
		return json.toString();
	}
	
	public static String buildDrinks(Object drink) {
		JsonElement drinkElement = gson.toJsonTree(drink);
		JsonObject json = new JsonObject();
		json.add("drink", drinkElement);
		return json.toString();
	}
	
	public static String buildSnack(SnacksBean snack) {
		JsonElement snackElement = gson.toJsonTree(snack);
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("snack", snackElement);
		return jsonObject.toString();
	}
	
	public static String buildMovieSnack(MovieBean movie, SnacksBean snack) {
		JsonElement movieElement = gson.toJsonTree(movie);
		JsonElement snackElement = gson.toJsonTree(snack);
		JsonObject object = new JsonObject();
		object.add("movie", movieElement);
		object.add("snack", snackElement);
		return object.toString();
	}
	
	public static String buildMovieSnackDrink(MovieBean movie, SnacksBean snack, Object object) {
		JsonElement movieElement = gson.toJsonTree(movie);
		JsonElement snackElement = gson.toJsonTree(snack);
		JsonElement drinkElement = gson.toJsonTree(object);
		JsonObject json = new JsonObject();
		json.add("movie", movieElement);
		json.add("snack", snackElement);
		json.add("drink", drinkElement);
		return json.toString();
	}
	
	public static String buildMovieDrink(MovieBean movie, Object object) {
		JsonElement movieElement = gson.toJsonTree(movie);
		JsonElement drinkElement = gson.toJsonTree(object);
		JsonObject json = new JsonObject();
		json.add("movie", movieElement);
		json.add("drink", drinkElement);
		return json.toString();
	}
}
