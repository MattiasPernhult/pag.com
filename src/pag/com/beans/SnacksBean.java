package pag.com.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pag.com.models.Links;

public class SnacksBean implements Serializable {

	private static final long serialVersionUID = -3168349974480377280L;
	
	private int id;
	private List<Links> links;
	private String name;
	private String description;
	private String kind;
	private String brand;
	private String posterURL;

	public SnacksBean() {
		links = new ArrayList<Links>();
	}
	
	public void setLinks(Links link) {
		links.add(link);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPosterURL() {
		return posterURL;
	}
	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}
}
