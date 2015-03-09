package pag.com.beans;

import java.io.Serializable;

public class BeerBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;

	private String name;
	private String description;
	private String posterURL;

	public BeerBean() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPosterPath() {
		return posterURL;
	}

	public void setPosterPath(String posterPath) {
		this.posterURL = posterPath;
	}
}
