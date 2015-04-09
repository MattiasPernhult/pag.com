package pag.com.builders;

import java.util.List;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import pag.com.beans.MovieBean;

import com.google.gson.Gson;

public class CustomResponseBuilder {

	private static Gson gson = new Gson();

	public static <T> ResponseBuilder buildResponseForObject(T object,
			Request request) {
		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);
		cc.setPrivate(true);

		EntityTag etag = new EntityTag(Integer.toString(object.hashCode()));
		ResponseBuilder builder = request.evaluatePreconditions(etag);

		if (builder == null) {
			builder = Response.ok(gson.toJson(object));
			builder.tag(etag);
		}
		builder.cacheControl(cc);
		return builder;
	}

	public static <T> ResponseBuilder buildResponseForList(List<T> list,
			Request request) {
		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);
		cc.setPrivate(true);

		EntityTag etag = new EntityTag(Integer.toString(list.hashCode()));
		ResponseBuilder builder = request.evaluatePreconditions(etag);

		if (builder == null) {
			builder = Response.ok(gson.toJson(list));
			builder.tag(etag);
		}
		builder.cacheControl(cc);
		return builder;
	}
}
