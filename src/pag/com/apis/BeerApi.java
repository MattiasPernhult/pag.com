package pag.com.apis;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import pag.com.beans.BeerBean;
import pag.com.builders.CustomResponseBuilder;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

@Path("/beers")
public class BeerApi {

	@GET 
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getRandomBeers(@Context Request request) {
		List<BeerBean> beers = new ArrayList<BeerBean>();
		int iterator = 0;
		
		while (iterator < 5) {
			String response = HttpManager.getData("http://api.brewerydb.com/v2/beer/random/?key=8116acd427ca82e9b5ac71c5e7d617bb&hasLabels=Y");
			
			BeerBean beer = JSONParser.parseBeer(response);
			if (beer != null) {
				beers.add(beer);
				iterator++;
			}
		}
		ResponseBuilder builder = CustomResponseBuilder.buildResponseForList(beers, request);
		return builder.build();
	}
}
