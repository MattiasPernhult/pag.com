package pag.com.parsers;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pag.com.beans.MovieBean;

public class JSONParser {

	public static List<MovieBean> parseMovie(String content) {
		try {
			JSONObject object = new JSONObject(content);
			JSONArray ar = object.getJSONArray("results");
			List<MovieBean> beans = new ArrayList<>();
			
			for (int i = 0; i < ar.length(); i++) {
				
				JSONObject obj = ar.getJSONObject(i);
				MovieBean bean = new MovieBean();
				bean.setTitle(obj.getString("title"));
				
				beans.add(bean);
			}
			return beans;
		} catch (JSONException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
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
			String plot = object.getString("Plot");
			if (plot.equals("N/A")) {
				return "There is no plot for this movie yet";
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
