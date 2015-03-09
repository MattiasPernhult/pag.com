package pag.com.beans;

import java.io.Serializable;

public class WineBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;
	
	private String name;
	private String posterURL;
	private String grape;
	private String winery;
	private String region;
	private double rating;
	
	public WineBean() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosterURL() {
		return posterURL;
	}
	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}
	public String getGrape() {
		return grape;
	}
	public void setGrape(String grape) {
		this.grape = grape;
	}
	public String getWinery() {
		return winery;
	}
	public void setWinery(String winery) {
		this.winery = winery;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
