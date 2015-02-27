package pag.com.beans;

import java.io.Serializable;

public class MovieBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;
	
	private String title; 
	private String plot;
	private double rating;

	public MovieBean() {
		
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
