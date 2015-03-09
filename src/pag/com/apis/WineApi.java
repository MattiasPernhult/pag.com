package pag.com.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import pag.com.beans.WineBean;
import pag.com.builders.CustomResponseBuilder;
import pag.com.connections.HttpManager;
import pag.com.parsers.JSONParser;

@Path("/wines")
public class WineApi {

	@GET 
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/red")
	public Response getRedWines(@Context Request request) {
		String response = HttpManager.getData("http://api.snooth.com/wines/?akey=hepr7g1ie179vnororwa7maofkymji7tqj38ne9qmpzenari&q=wine&color=red&t=wine&c=SE&s=&mr=3&n=10&xp=20");
	
		List<WineBean> wines = JSONParser.wineParser(response);
		
		ResponseBuilder builder = CustomResponseBuilder.buildResponseForList(wines, request);
		return builder.build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/white")
	public Response getWithWines(@Context Request request) {
		String response = HttpManager.getData("http://api.snooth.com/wines/?akey=hepr7g1ie179vnororwa7maofkymji7tqj38ne9qmpzenari&q=wine&color=white&t=wine&c=SE&s=&mr=3&n=10&xp=20");
	
		List<WineBean> wines = JSONParser.wineParser(response);
		
		ResponseBuilder builder = CustomResponseBuilder.buildResponseForList(wines, request);
		return builder.build();
	}
}
