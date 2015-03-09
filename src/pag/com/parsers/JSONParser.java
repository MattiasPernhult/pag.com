package pag.com.parsers;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pag.com.beans.ActorBean;
import pag.com.beans.BeerBean;
import pag.com.beans.MovieBean;
import pag.com.beans.SnacksBean;
import pag.com.beans.TestBean;
import pag.com.beans.TvBean;
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
			System.out.println(e.getMessage() + "parseMovieGenre och content: " + content);
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
				snacks.add(snack);
			}
			return snacks;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<TvBean> parseTv(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			List<TvBean> tvshows = new ArrayList<TvBean>();
			
			for (int i = 0; i < 18; i++) {
				JSONObject obj = ar.getJSONObject(i);
				TvBean tv = new TvBean();
				tv.setTitle(obj.getString("name"));
				tv.setPosterURL("http://image.tmdb.org/t/p/w500/" + obj.get("poster_path"));
				tvshows.add(tv);
			}
			return tvshows;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage());
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
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static TestBean parse(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			JSONObject obj = ar.getJSONObject(0);
			TestBean bean = new TestBean();
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
				actor.setPosterURL("http://image.tmdb.org/t/p/w500" + obj.getString("profile_path"));
				
				actors.add(actor);
			}
			return actors;	
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<MovieBean> parseMovie(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			List<MovieBean> beans = new ArrayList<MovieBean>();
			
			for (int i = 0; i <  18; i++) {
				
				JSONObject obj = ar.getJSONObject(i);
				MovieBean bean = new MovieBean();
				
				bean.setTitle(obj.getString("title"));
				bean.setId(obj.getInt("id"));
				bean.setRating(obj.getDouble("vote_average"));
				bean.setPosterURL("http://image.tmdb.org/t/p/w500/" + obj.getString("poster_path"));
				
				beans.add(bean);
			}
			return beans;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
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
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<MovieBean> parseMovieToplist(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			List<MovieBean> beans = new ArrayList<>();
			
			for (int i = 0; i < ar.length(); i++) {
				
				JSONObject obj = ar.getJSONObject(i);
				MovieBean bean = new MovieBean();
				bean.setTitle(obj.getString("title"));
				bean.setRating(obj.getDouble("vote_average"));
				bean.setPosterURL("http://image.tmdb.org/t/p/w500/" + obj.getString("poster_path"));
				
				beans.add(bean);
			}
			return beans;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
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
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static double parseTitleToRating(String content) {
		try {
			JSONObject object = new JSONObject(content);
			double rating = object.getDouble("imdbRating");
			return rating;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage());
		}
		return 0.0;
	}
}
