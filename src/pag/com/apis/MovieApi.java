package pag.com.apis;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import pag.com.beans.ActorBean;
import pag.com.beans.MovieBean;
import pag.com.beans.VideoBean;
import pag.com.builders.CustomJSONBuilder;
import pag.com.builders.CustomResponseBuilder;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;
import pag.com.util.Helper;

@Path("/movies")
public class MovieApi {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/actors")
	public Response getActors(@PathParam("id") int movieId,
			@Context Request request) {
		String response = HttpManager
				.getData("http://api.themoviedb.org/3/movie/" + movieId
						+ "/casts?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		List<ActorBean> actors = JSONParser.parseActors(response);
		return Response.ok(CustomJSONBuilder.buildActors(actors)).build();
	}

	@GET
	@Path("/video/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVideo(@PathParam("id") int id, @Context Request request) {
		String response = HttpManager
				.getData("http://api.themoviedb.org/3/movie/" + id
						+ "/videos?api_key=ab558a8fff48745b07832d7ee3bb1d1b");
		VideoBean bean = JSONParser.parseVideo(response);
		ResponseBuilder builder = CustomResponseBuilder.buildResponseForObject(
				bean, request);
		return builder.build();
	}
}