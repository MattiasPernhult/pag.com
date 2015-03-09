package pag.com.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import pag.com.beans.MovieBean;
import pag.com.beans.TvBean;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.Helper;

@Path("/tv")
public class TvApi {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getNewShows() {
		String response = HttpManager.getData("http://api.themoviedb.org/3/discover/tv?first_air_date.gte=2014-08-01&api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		
		List<TvBean> tvshows = JSONParser.parseTv(response);
		
		for (TvBean tv : tvshows) {
			String newStr = Helper.replaceSpaces(tv.getTitle());
			String response2 = HttpManager.getData("http://www.omdbapi.com/?t=" + newStr);
			tv.setDescription(JSONParser.parseTitleToPlot(response2));
			tv.setRating(JSONParser.parseTitleToRating(response2));
		}
		Gson gson = new Gson();
		
		return Response.ok(gson.toJson(tvshows)).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}
	
}
