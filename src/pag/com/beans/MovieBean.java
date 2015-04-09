package pag.com.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pag.com.models.Links;

public class MovieBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;
	
	private List<String> genres = new ArrayList<String>();
	
	private int id;
	public int getId() {
		return id;
	}
	
	public List<String> getGenres() {
		return this.genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	private List<Links> links;
	private String title; 
	private String plot;
	private double rating;
	private String posterURL; 

	public MovieBean() {
		links = new ArrayList<Links>();
	}
	
	public void setLinks(Links link) {
		links.add(link);
	}
	
	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
}
