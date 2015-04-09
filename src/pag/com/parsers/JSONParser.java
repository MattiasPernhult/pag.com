package pag.com.parsers;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pag.com.beans.ActorBean;
import pag.com.beans.BeerBean;
import pag.com.models.Links;
import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;
import pag.com.beans.VideoBean;
import pag.com.beans.WineBean;
import pag.com.util.Helper;

public class JSONParser {

	public static List<String> parseMovieGenre(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("genres");
			List<String> genres = new ArrayList<String>();

			for (int i = 0; i < ar.length(); i++) {
				JSONObject obj = ar.getJSONObject(i);
				String genre = obj.getString("name");
				genres.add(genre);
			}
			return genres;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "parseMovieGenre och content: "
					+ content);
			return null;
		}
	}

	public static List<SnacksBean> parseSnacks(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("snacks");
			List<SnacksBean> snacks = new ArrayList<SnacksBean>();

			for (int i = 0; i < ar.length(); i++) {
				JSONObject obj = ar.getJSONObject(i);
				SnacksBean snack = new SnacksBean();
				snack.setId(obj.getInt("id"));
				snack.setName(obj.getString("name"));
				snack.setDescription(obj.getString("description"));
				snack.setKind(obj.getString("kind"));
				snack.setBrand(obj.getString("brand"));
				snack.setPosterURL(obj.getString("posterURL"));

				JSONArray arr = obj.getJSONArray("links");

				for (int j = 0; j < arr.length(); j++) {
					JSONObject json = arr.getJSONObject(j);
					Links link = new Links();
					link.setName(json.getString("name"));
					link.setLink(json.getString("link"));
					snack.setLinks(link);
				}
				snacks.add(snack);
			}
			return snacks;
		} catch (JSONException e) {
			System.out.println("Snacks " + e.getMessage());
			return null;
		}
	}

	public static List<WineBean> wineParser(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("wines");
			List<WineBean> wines = new ArrayList<WineBean>();

			for (int i = 0; i < ar.length(); i++) {
				JSONObject obj = ar.getJSONObject(i);
				WineBean wine = new WineBean();
				wine.setName(obj.getString("name"));
				wine.setPosterURL(obj.getString("image"));
				wine.setGrape(obj.getString("varietal"));
				wine.setWinery(obj.getString("winery"));
				wine.setRating(obj.getDouble("snoothrank"));
				String region = obj.getString("region");
				wine.setRegion(Helper.fixWineRegion(region));
				wines.add(wine);
			}
			return wines;
		} catch (JSONException e) {
			System.out.println(e.getMessage() + " wineParser");
			return null;
		}
	}

	public static VideoBean parseVideo(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			JSONObject obj = ar.getJSONObject(0);
			VideoBean bean = new VideoBean();
			bean.videoId = obj.getString("key");
			return bean;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static List<ActorBean> parseActors(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("cast");
			List<ActorBean> actors = new ArrayList<ActorBean>();

			for (int i = 0; i < 4; i++) {
				JSONObject obj = ar.getJSONObject(i);
				ActorBean actor = new ActorBean();

				actor.setCharacter(obj.getString("character"));
				actor.setActorName(obj.getString("name"));
				actor.setPosterURL("http://image.tmdb.org/t/p/w500"
						+ obj.getString("profile_path"));

				actors.add(actor);
			}
			return actors;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static List<MovieBean> parseMovie(String content) {
		if (content == null) {
			return null;
		}
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			List<MovieBean> beans = new ArrayList<MovieBean>();

			for (int i = 0; i < 18; i++) {
				JSONObject obj = ar.getJSONObject(i);
				MovieBean bean = new MovieBean();

				bean.setTitle(obj.getString("title"));
				bean.setId(obj.getInt("id"));
				bean.setRating(obj.getDouble("vote_average"));
				bean.setPosterURL("http://image.tmdb.org/t/p/w500/"
						+ obj.getString("poster_path"));
				
				beans.add(bean);
			}
			return beans;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage() + "Movie nr1");
			return null;
		}
	}

	public static BeerBean parseBeer(String content) {
		try {
			JSONObject allBeer = new JSONObject(content);
			JSONObject beerData = allBeer.getJSONObject("data");
			BeerBean beer = new BeerBean();
			beer.setName(beerData.getString("name"));
			String description = beerData.getString("description");
			if (description == null) {
				return null;
			}
			beer.setDescription(description);
			JSONObject beerPoster = beerData.getJSONObject("labels");
			beer.setPosterPath(beerPoster.getString("medium"));
			return beer;
		} catch (JSONException e) {
			System.out.println("Beer " + e.getMessage());
			return null;
		}
	}

	public static String parseTitleToPlot(String content) {
		try {
			JSONObject object = new JSONObject(content);
			String plot = object.getString("overview");
			if (plot.equals("N/A")) {
				return "There is no plot for this movie/tv show yet";
			}
			return plot;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage() + " to parseTitleToPlot");
			return null;
		}
	}
}
